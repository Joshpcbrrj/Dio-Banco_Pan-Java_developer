"""Carrega e valida o banco de perguntas do simulado (docs/simulado/perguntas.yml)."""
import os

import yaml

CAMPOS = ("modulo", "aula", "pergunta", "opcoes", "resposta", "explicacao")


def carregar(docs_dir):
    caminho = os.path.join(docs_dir, "simulado", "perguntas.yml")
    with open(caminho, encoding="utf-8") as f:
        perguntas = yaml.safe_load(f)

    erros = []
    for n, p in enumerate(perguntas, 1):
        faltando = [c for c in CAMPOS if c not in p]
        if faltando:
            erros.append(f"pergunta {n}: faltam os campos {faltando}")
            continue
        if len(p["opcoes"]) != 4:
            erros.append(f"pergunta {n}: precisa de 4 opções (tem {len(p['opcoes'])})")
        if not (isinstance(p["resposta"], int) and 0 <= p["resposta"] < len(p["opcoes"])):
            erros.append(f"pergunta {n}: resposta {p['resposta']!r} fora do intervalo")
        if not os.path.exists(os.path.join(docs_dir, p["aula"])):
            erros.append(f"pergunta {n}: aula não encontrada ({p['aula']})")
        if not 1 <= p["modulo"] <= 8:
            erros.append(f"pergunta {n}: módulo inválido ({p['modulo']})")
    if erros:
        raise ValueError("Erros no banco de perguntas do simulado:\n  " + "\n  ".join(erros))
    return perguntas
