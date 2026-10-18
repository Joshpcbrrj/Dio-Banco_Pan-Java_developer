"""Gera o EPUB do material a partir dos Markdowns em docs/.

Uso:  python scripts/build_epub.py

Precisa do Pandoc e do mermaid-cli (mmdc). Os caminhos podem ser trocados pelas
variáveis de ambiente PANDOC e MMDC. A capa em PNG é gerada com rsvg-convert,
se ele estiver instalado; caso contrário, usa a capa em SVG.
"""
import glob
import os
import posixpath
import re
import shutil
import subprocess
import sys
import urllib.parse

sys.path.insert(0, os.path.dirname(__file__))
from simulado import carregar  # noqa: E402
from transform import ALERTS, strip_nav, walk  # noqa: E402

ROOT = os.path.abspath(os.path.join(os.path.dirname(__file__), ".."))
BUILD = os.path.join(ROOT, "build", "epub")
OUTPUT = os.path.join(ROOT, "docs", "downloads", "java-developer-banco-pan.epub")
REPO_URL = "https://github.com/Joshpcbrrj/Dio-Banco_Pan-Java_developer/blob/main/"

MODULES = {
    "01-principios": "Módulo 1 · Princípios de desenvolvimento de software",
    "02-java": "Módulo 2 · Conhecendo a linguagem Java",
    "03-desafios-basicos": "Módulo 3 · Desafios de código: algoritmos básicos",
    "04-poo": "Módulo 4 · Programação orientada a objetos",
    "05-banco-de-dados": "Módulo 5 · Bancos de dados SQL e NoSQL",
    "06-spring": "Módulo 6 · Produtividade com Spring Framework",
    "07-desafios-intermediarios": "Módulo 7 · Desafios de código: algoritmos intermediários",
    "08-extras": "Módulo 8 · Extras (além do curso)",
}

LINK_RE = re.compile(r'(!?)\[([^\]]*)\]\(([^)\s]+)((?:\s+"[^"]*")?)\)')
IMG_RE = re.compile(r'<img\s+src="([^"]+)"\s+alt="([^"]*)"[^>]*>')
HEADING_RE = re.compile(r"^(#{1,5}) ")


def tool(env, name):
    path = os.environ.get(env) or shutil.which(name)
    if not path:
        sys.exit(f"Ferramenta não encontrada: {name} (defina a variável {env})")
    return path


def lesson_id(rel):
    return "l-" + re.sub(r"[^a-z0-9]+", "-", rel.lower().removesuffix(".md")).strip("-")


def lessons():
    found = []
    for module in MODULES:
        for path in sorted(glob.glob(os.path.join(ROOT, "docs", module, "*.md"))):
            found.append((module, posixpath.relpath(path.replace("\\", "/"), ROOT.replace("\\", "/"))))
    found.append(("apendice", "docs/guia-rapido.md"))
    return found


class Book:
    def __init__(self):
        self.mermaid_count = 0
        self.mmdc = tool("MMDC", "mmdc")
        self.ids = {rel: lesson_id(rel) for _, rel in lessons()}

    def resolve(self, target, rel, image):
        if re.match(r"^[a-z]+:", target) or target.startswith("#"):
            return target
        path, _, anchor = target.partition("#")
        full = posixpath.normpath(posixpath.join(posixpath.dirname(rel), urllib.parse.unquote(path)))
        if full in self.ids:
            return "#" + self.ids[full]
        if not os.path.exists(os.path.join(ROOT, full)):
            return target
        return full if image else REPO_URL + urllib.parse.quote(full)

    def mermaid(self, code):
        self.mermaid_count += 1
        base = os.path.join(BUILD, f"diagrama-{self.mermaid_count:02d}")
        with open(base + ".mmd", "w", encoding="utf-8") as f:
            f.write(code)
        cmd = [self.mmdc, "-i", base + ".mmd", "-o", base + ".png", "-s", "2", "-b", "white",
               "-p", os.path.join(ROOT, "scripts", "puppeteer-config.json")]
        result = subprocess.run(cmd, capture_output=True, text=True, shell=sys.platform == "win32")
        if result.returncode != 0:
            sys.exit(f"Erro ao renderizar o diagrama {self.mermaid_count}:\n{code}\n\n{result.stderr}")
        rel = posixpath.relpath(base.replace("\\", "/") + ".png", ROOT.replace("\\", "/"))
        return [f"![Diagrama]({rel})", ""]

    def convert(self, rel):
        text = strip_nav(open(os.path.join(ROOT, rel), encoding="utf-8").read())
        answers = []

        def line(l):
            l = IMG_RE.sub(lambda m: f"![{m.group(2)}]({m.group(1)})", l)
            l = LINK_RE.sub(lambda m: f"{m.group(1)}[{m.group(2)}]({self.resolve(m.group(3), rel, m.group(1) == '!')}{m.group(4)})", l)
            h = HEADING_RE.match(l)
            return "#" + l if h else l

        def alert(kind, body):
            css, title, icon = ALERTS[kind]
            return [f"::: {{.callout .{css}}}", f"**{icon} {title}**", ""] + [line(b) for b in body] + [":::", ""]

        def details(summary, body):
            if summary == "Ver resposta":
                answers.append(body)
                return [f"*Resposta {len(answers)} no fim da aula.*", ""]
            return [f"**{summary}**", ""] + [line(b) for b in body] + [""]

        out = walk(text, alert, details, self.mermaid, line)
        # Título da aula vira capítulo com id fixo, para os links entre aulas funcionarem
        out = re.sub(r"^## (.+)$", lambda m: f"## {m.group(1)} {{#{self.ids[rel]}}}", out, count=1, flags=re.M)
        if answers:
            out += "\n\n### ✅ Respostas dos exercícios\n"
            for n, body in enumerate(answers, 1):
                out += f"\n**Resposta {n}**\n\n" + "\n".join(line(b) for b in body) + "\n"
        return out

    def simulado(self):
        perguntas = carregar(os.path.join(ROOT, "docs"))
        letras = "abcd"
        modulos = list(MODULES.values())
        out = ["# Apêndice B · Simulado", "",
               f"São **{len(perguntas)} perguntas** de múltipla escolha sobre todo o conteúdo. "
               "Anote suas respostas e confira no **gabarito comentado**, no fim deste apêndice.", "",
               "No site, o simulado tem **nota automática** e sorteia perguntas novas a cada tentativa: "
               "<https://joshpcbrrj.github.io/Dio-Banco_Pan-Java_developer/simulado/>", ""]
        gabarito = ["## Gabarito comentado", ""]
        atual = None
        for n, p in enumerate(perguntas, 1):
            if p["modulo"] != atual:
                atual = p["modulo"]
                out += [f"## {modulos[atual - 1]}", ""]
            out += [f"**{n}.** {p['pergunta']}", ""]
            out += [f"{letras[i]}) {op}" for i, op in enumerate(p["opcoes"])] + [""]
            m = re.match(r"(\d+)-[^/]+/(\d+)-", p["aula"])
            numero = f"{int(m.group(1))}.{int(m.group(2))}"
            link = "#" + self.ids["docs/" + p["aula"]]
            gabarito += [f"**{n}. {letras[p['resposta']]})** {p['explicacao']} [Revisar a aula {numero}]({link})", ""]
        return "\n".join(out + gabarito)

    def build(self):
        parts = [open(os.path.join(ROOT, "epub", "introducao.md"), encoding="utf-8").read()]
        current = None
        for module, rel in lessons():
            if module != current:
                current = module
                title = MODULES.get(module, "Apêndice A · Guia rápido e glossário")
                parts.append(f"# {title}\n")
            parts.append(self.convert(rel))
        parts.append(self.simulado())
        return "\n\n".join(parts)


def cover():
    svg = os.path.join(ROOT, "epub", "capa.svg")
    rsvg = shutil.which("rsvg-convert")
    if rsvg:
        png = os.path.join(BUILD, "capa.png")
        subprocess.run([rsvg, "-w", "1600", "-h", "2400", svg, "-o", png], check=True)
        return png
    print("rsvg-convert não encontrado: usando a capa em SVG")
    return svg


def main():
    shutil.rmtree(BUILD, ignore_errors=True)
    os.makedirs(BUILD)
    os.makedirs(os.path.dirname(OUTPUT), exist_ok=True)
    book = Book()
    source = os.path.join(BUILD, "livro.md")
    with open(source, "w", encoding="utf-8", newline="\n") as f:
        f.write(book.build())
    cmd = [tool("PANDOC", "pandoc"), source,
           "--from", "markdown+emoji",
           "--metadata-file", os.path.join(ROOT, "epub", "metadata.yaml"),
           "--css", os.path.join(ROOT, "epub", "epub.css"),
           "--epub-cover-image", cover(),
           "--toc", "--toc-depth=2", "--split-level=2", "--mathml",
           "--resource-path", ROOT,
           "-o", OUTPUT]
    subprocess.run(cmd, check=True, cwd=ROOT)
    print(f"EPUB gerado: {OUTPUT} ({book.mermaid_count} diagramas, {os.path.getsize(OUTPUT) // 1024} KB)")


if __name__ == "__main__":
    main()
