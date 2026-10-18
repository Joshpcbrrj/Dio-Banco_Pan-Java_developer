"""Hooks do MkDocs.

- Adapta os Markdowns escritos para o GitHub antes de renderizar.
- Troca links para arquivos fora de docs/ (projetos, README, LICENSE) por links do GitHub.
- Converte o banco de perguntas do simulado (YAML) para JSON, lido pelo site.
"""
import json
import os
import posixpath
import re
import sys
import urllib.parse

sys.path.insert(0, os.path.dirname(__file__))
from simulado import carregar  # noqa: E402
from transform import FENCE_RE, _closes, to_site  # noqa: E402

REPO_URL = "https://github.com/Joshpcbrrj/Dio-Banco_Pan-Java_developer/"
LINK_RE = re.compile(r"(!?)\[([^\]]*)\]\(([^)\s]+)\)")


def _fora_de_docs(markdown, src_uri):
    def troca(m):
        alvo = m.group(3)
        if m.group(1) or re.match(r"^[a-z]+:", alvo) or alvo.startswith("#"):
            return m.group(0)
        caminho = posixpath.normpath(posixpath.join("docs", posixpath.dirname(src_uri), urllib.parse.unquote(alvo)))
        if caminho.startswith("docs/"):
            return m.group(0)
        tipo = "tree" if alvo.endswith("/") else "blob"
        return f"[{m.group(2)}]({REPO_URL}{tipo}/main/{urllib.parse.quote(caminho)})"

    linhas, cerca = [], None
    for linha in markdown.split("\n"):
        f = FENCE_RE.match(linha)
        if cerca:
            if _closes(linha, cerca):
                cerca = None
        elif f:
            cerca = f.group(2)
        else:
            linha = LINK_RE.sub(troca, linha)
        linhas.append(linha)
    return "\n".join(linhas)


def on_page_markdown(markdown, page, config, files):
    return to_site(_fora_de_docs(markdown, page.file.src_uri))


def on_post_build(config):
    perguntas = carregar(config["docs_dir"])
    destino = os.path.join(config["site_dir"], "simulado", "perguntas.json")
    os.makedirs(os.path.dirname(destino), exist_ok=True)
    with open(destino, "w", encoding="utf-8") as f:
        json.dump(perguntas, f, ensure_ascii=False)
