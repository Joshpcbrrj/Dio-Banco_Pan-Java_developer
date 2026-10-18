"""Transformações compartilhadas entre o site (MkDocs) e o EPUB (Pandoc).

Os arquivos em docs/ são escritos para serem lidos direto no GitHub. Este módulo
adapta, em memória, os recursos específicos do GitHub para cada formato:

- alertas `> [!NOTE]`, `> [!TIP]` etc.
- blocos recolhíveis `<details><summary>...</summary>...</details>`
- a navegação "Anterior | Índice | Próximo" no fim de cada aula
"""
import re

ALERTS = {
    "NOTE": ("note", "Nota", "📝"),
    "TIP": ("tip", "Dica", "💡"),
    "IMPORTANT": ("info", "Importante", "❗"),
    "WARNING": ("warning", "Atenção", "⚠️"),
    "CAUTION": ("danger", "Cuidado", "🛑"),
}

FENCE_RE = re.compile(r"^(\s*)(`{3,}|~{3,})(.*)$")
ALERT_RE = re.compile(r"^>\s*\[!(NOTE|TIP|IMPORTANT|WARNING|CAUTION)\]\s*$")
SUMMARY_RE = re.compile(r"^\s*<summary>(.*)</summary>\s*$")
NAV_RE = re.compile(r'\n---\n\n<div align="center">\n\n[^\n]*Índice[^\n]*\n\n</div>\s*$')
BACK_RE = re.compile(r"^\[🏠 Voltar ao índice\]\([^)]*\)[ \t]*$", re.M)
EMPTY_CENTER_RE = re.compile(r'\n(---\n\n)?<div align="center">\s*</div>\s*$')


def strip_nav(text):
    """Remove a navegação no fim das aulas e os links "Voltar ao índice"."""
    text = NAV_RE.sub("\n", text)
    text = BACK_RE.sub("", text)
    return EMPTY_CENTER_RE.sub("\n", text)


def _closes(line, fence):
    s = line.strip()
    return bool(s) and set(s) == {fence[0]} and len(s) >= len(fence)


def _trim(lines):
    while lines and not lines[0].strip():
        lines = lines[1:]
    while lines and not lines[-1].strip():
        lines = lines[:-1]
    return lines


def walk(text, on_alert, on_details, on_mermaid=None, on_line=None):
    """Percorre o Markdown ignorando blocos de código e chama os tratadores."""
    lines = text.split("\n")
    out, i, fence = [], 0, None

    def blank_before():
        if out and out[-1].strip():
            out.append("")

    while i < len(lines):
        line = lines[i]
        m = FENCE_RE.match(line)
        if fence:
            out.append(line)
            if _closes(line, fence):
                fence = None
            i += 1
            continue
        if m:
            if on_mermaid and m.group(1) == "" and m.group(2) == "```" and m.group(3).strip() == "mermaid":
                j, body = i + 1, []
                while j < len(lines) and not _closes(lines[j], "```"):
                    body.append(lines[j])
                    j += 1
                blank_before()
                out.extend(on_mermaid("\n".join(body)))
                i = j + 1
                continue
            fence = m.group(2)
            out.append(line)
            i += 1
            continue
        am = ALERT_RE.match(line)
        if am:
            body, i = [], i + 1
            while i < len(lines) and lines[i].startswith(">"):
                rest = lines[i][1:]
                body.append(rest[1:] if rest.startswith(" ") else rest)
                i += 1
            blank_before()
            out.extend(on_alert(am.group(1), _trim(body)))
            continue
        if line.strip() == "<details>" and i + 1 < len(lines) and SUMMARY_RE.match(lines[i + 1]):
            summary = SUMMARY_RE.match(lines[i + 1]).group(1)
            j, body, inner = i + 2, [], None
            while j < len(lines):
                cur = lines[j]
                fm = FENCE_RE.match(cur)
                if inner:
                    if _closes(cur, inner):
                        inner = None
                elif fm:
                    inner = fm.group(2)
                elif cur.strip() == "</details>":
                    break
                body.append(cur)
                j += 1
            blank_before()
            out.extend(on_details(summary, _trim(body)))
            i = j + 1
            continue
        out.append(on_line(line) if on_line else line)
        i += 1
    return "\n".join(out)


# ---------------------------------------------------------------- site (MkDocs)

def _indent(lines):
    return ["    " + l if l.strip() else "" for l in lines]


def to_site(text):
    def alert(kind, body):
        css, title, _ = ALERTS[kind]
        return [f'!!! {css} "{title}"', ""] + _indent(body) + [""]

    def details(summary, body):
        css = "success" if summary == "Ver resposta" else "note"
        return [f'??? {css} "{summary}"', ""] + _indent(body) + [""]

    return walk(strip_nav(text), alert, details)
