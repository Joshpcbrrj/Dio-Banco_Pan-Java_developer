/* Simulado com correção automática.
 * As perguntas vêm de docs/simulado/perguntas.yml, convertido para
 * perguntas.json durante o build do site (scripts/mkdocs_hooks.py). */
(function () {
  "use strict";

  var MODULOS = {
    1: "Princípios", 2: "Java", 3: "Desafios básicos", 4: "POO",
    5: "Banco de dados", 6: "Spring", 7: "Desafios intermediários", 8: "Extras"
  };
  var TOTAL_COMPLETO = 40;
  var APROVACAO = 0.7;
  var CHAVE_MELHOR = "simulado-java-melhor-nota";

  function esc(s) {
    return String(s).replace(/[&<>"]/g, function (c) {
      return { "&": "&amp;", "<": "&lt;", ">": "&gt;", '"': "&quot;" }[c];
    });
  }

  // Formata `código` e **negrito** (o texto é escapado antes)
  function fmt(s) {
    return esc(s)
      .replace(/`([^`]+)`/g, "<code>$1</code>")
      .replace(/\*\*([^*]+)\*\*/g, "<strong>$1</strong>");
  }

  function embaralhar(lista) {
    var a = lista.slice();
    for (var i = a.length - 1; i > 0; i--) {
      var j = Math.floor(Math.random() * (i + 1));
      var t = a[i]; a[i] = a[j]; a[j] = t;
    }
    return a;
  }

  // "02-java/04-tipos-e-variaveis.md" -> link "../02-java/04-tipos-e-variaveis/" e rótulo "2.4"
  function aula(caminho) {
    var m = caminho.match(/^(\d+)-[^/]+\/(\d+)-/);
    return {
      url: "../" + caminho.replace(/\.md$/, "/"),
      numero: m ? parseInt(m[1], 10) + "." + parseInt(m[2], 10) : ""
    };
  }

  function lerMelhor() {
    try { return JSON.parse(localStorage.getItem(CHAVE_MELHOR)); } catch (e) { return null; }
  }

  function salvarMelhor(acertos, total) {
    try {
      var atual = lerMelhor();
      if (!atual || acertos / total > atual.acertos / atual.total) {
        localStorage.setItem(CHAVE_MELHOR, JSON.stringify({ acertos: acertos, total: total }));
        return true;
      }
    } catch (e) { /* armazenamento indisponível: segue sem guardar */ }
    return false;
  }

  function telaInicial(raiz, perguntas) {
    var porModulo = {};
    perguntas.forEach(function (p) { porModulo[p.modulo] = (porModulo[p.modulo] || 0) + 1; });

    var opcoes = '<option value="completo">Simulado completo: ' + TOTAL_COMPLETO +
      " perguntas sorteadas de todos os módulos</option>";
    Object.keys(MODULOS).forEach(function (n) {
      if (porModulo[n]) {
        opcoes += '<option value="' + n + '">Módulo ' + n + " · " + MODULOS[n] +
          " (" + porModulo[n] + " perguntas)</option>";
      }
    });

    var melhor = lerMelhor();
    var textoMelhor = melhor
      ? '<p class="sim-melhor">🏆 Sua melhor nota no simulado completo: <strong>' + melhor.acertos +
        "/" + melhor.total + "</strong> (" + Math.round(100 * melhor.acertos / melhor.total) + "%)</p>"
      : "";

    raiz.innerHTML =
      '<div class="sim-cartao">' +
      "<p>O banco tem <strong>" + perguntas.length + " perguntas</strong>. A cada tentativa as perguntas " +
      "e as alternativas são sorteadas de novo. Para ser aprovado, acerte pelo menos <strong>" +
      Math.round(APROVACAO * 100) + "%</strong>.</p>" +
      '<label class="sim-label" for="sim-modo">Escolha o simulado</label>' +
      '<select id="sim-modo" class="sim-select">' + opcoes + "</select>" +
      textoMelhor +
      '<button type="button" class="md-button md-button--primary" id="sim-comecar">Começar</button>' +
      "</div>";

    raiz.querySelector("#sim-comecar").addEventListener("click", function () {
      var modo = raiz.querySelector("#sim-modo").value;
      var selecao = modo === "completo"
        ? embaralhar(perguntas).slice(0, TOTAL_COMPLETO)
        : embaralhar(perguntas.filter(function (p) { return String(p.modulo) === modo; }));
      prova(raiz, perguntas, selecao, modo);
    });
  }

  function prova(raiz, perguntas, selecao, modo) {
    var html = '<div class="sim-progresso" id="sim-progresso">Respondidas: 0/' + selecao.length + "</div>";
    html += '<form id="sim-form">';
    selecao.forEach(function (p, i) {
      var ordem = embaralhar([0, 1, 2, 3].slice(0, p.opcoes.length));
      html += '<fieldset class="sim-questao" data-indice="' + i + '">' +
        '<legend><span class="sim-num">' + (i + 1) + ".</span> " + fmt(p.pergunta) + "</legend>";
      ordem.forEach(function (o) {
        html += '<label class="sim-opcao"><input type="radio" name="q' + i + '" value="' + o + '"> ' +
          "<span>" + fmt(p.opcoes[o]) + "</span></label>";
      });
      html += '<div class="sim-feedback" hidden></div></fieldset>';
    });
    html += '<button type="submit" class="md-button md-button--primary">Corrigir</button></form>';
    raiz.innerHTML = html;
    window.scrollTo({ top: raiz.offsetTop - 80 });

    var form = raiz.querySelector("#sim-form");
    var progresso = raiz.querySelector("#sim-progresso");
    form.addEventListener("change", function () {
      var feitas = form.querySelectorAll("input:checked").length;
      progresso.textContent = "Respondidas: " + feitas + "/" + selecao.length;
    });
    form.addEventListener("submit", function (ev) {
      ev.preventDefault();
      var faltam = selecao.length - form.querySelectorAll("input:checked").length;
      if (faltam > 0 && !window.confirm("Ainda faltam " + faltam + " pergunta(s). Corrigir mesmo assim?")) return;
      corrigir(raiz, perguntas, selecao, modo, form);
    });
  }

  function corrigir(raiz, perguntas, selecao, modo, form) {
    var acertos = 0;
    var porModulo = {};

    selecao.forEach(function (p, i) {
      var campo = form.querySelector('fieldset[data-indice="' + i + '"]');
      var marcada = campo.querySelector("input:checked");
      var escolha = marcada ? parseInt(marcada.value, 10) : -1;
      var certa = escolha === p.resposta;
      if (certa) acertos++;

      var m = porModulo[p.modulo] || (porModulo[p.modulo] = { acertos: 0, total: 0 });
      m.total++;
      if (certa) m.acertos++;

      campo.classList.add(certa ? "sim-certa" : "sim-errada");
      campo.querySelectorAll("input").forEach(function (inp) {
        inp.disabled = true;
        var v = parseInt(inp.value, 10);
        if (v === p.resposta) inp.parentNode.classList.add("sim-opcao-correta");
        else if (v === escolha) inp.parentNode.classList.add("sim-opcao-marcada");
      });

      var a = aula(p.aula);
      var fb = campo.querySelector(".sim-feedback");
      fb.innerHTML = "<p><strong>" + (certa ? "✅ Correto!" : escolha === -1 ? "⚪ Não respondida." : "❌ Incorreto.") +
        "</strong> " + fmt(p.explicacao) + '</p><p><a href="' + a.url + '">📖 Revisar a aula ' + a.numero + "</a></p>";
      fb.hidden = false;
    });

    var total = selecao.length;
    var pct = Math.round(100 * acertos / total);
    var aprovado = acertos / total >= APROVACAO;
    var recorde = modo === "completo" && salvarMelhor(acertos, total);

    var linhas = "";
    Object.keys(porModulo).sort(function (a, b) { return a - b; }).forEach(function (n) {
      var m = porModulo[n];
      linhas += "<tr><td>Módulo " + n + " · " + MODULOS[n] + "</td><td>" + m.acertos + "/" + m.total + "</td></tr>";
    });

    var resumo = document.createElement("div");
    resumo.className = "sim-cartao sim-resultado " + (aprovado ? "sim-aprovado" : "sim-reprovado");
    resumo.innerHTML =
      '<p class="sim-nota">' + acertos + "/" + total + " · " + pct + "%</p>" +
      "<p>" + (aprovado
        ? "🎉 <strong>Aprovado!</strong> Você domina bem o conteúdo."
        : "📚 <strong>Ainda não foi desta vez.</strong> Revise as aulas indicadas nas perguntas que você errou e tente de novo.") +
      (recorde ? " 🏆 Nova melhor nota!" : "") + "</p>" +
      '<table class="sim-tabela"><thead><tr><th>Módulo</th><th>Acertos</th></tr></thead><tbody>' + linhas + "</tbody></table>" +
      '<p class="sim-botoes">' +
      '<button type="button" class="md-button md-button--primary" id="sim-refazer">Refazer com novas perguntas</button> ' +
      '<button type="button" class="md-button" id="sim-voltar">Escolher outro simulado</button></p>';

    form.querySelector('button[type="submit"]').remove();
    raiz.querySelector("#sim-progresso").remove();
    raiz.insertBefore(resumo, raiz.firstChild);
    window.scrollTo({ top: raiz.offsetTop - 80, behavior: "smooth" });

    resumo.querySelector("#sim-refazer").addEventListener("click", function () {
      var nova = modo === "completo"
        ? embaralhar(perguntas).slice(0, TOTAL_COMPLETO)
        : embaralhar(perguntas.filter(function (p) { return String(p.modulo) === modo; }));
      prova(raiz, perguntas, nova, modo);
    });
    resumo.querySelector("#sim-voltar").addEventListener("click", function () {
      telaInicial(raiz, perguntas);
    });
  }

  function iniciar() {
    var raiz = document.getElementById("simulado");
    if (!raiz || raiz.dataset.pronto) return;
    raiz.dataset.pronto = "1";
    fetch("perguntas.json")
      .then(function (r) { if (!r.ok) throw new Error(r.status); return r.json(); })
      .then(function (perguntas) { telaInicial(raiz, perguntas); })
      .catch(function () {
        raiz.innerHTML = "<p>Não foi possível carregar as perguntas. Recarregue a página para tentar de novo.</p>";
      });
  }

  if (typeof document$ !== "undefined") document$.subscribe(iniciar);
  else document.addEventListener("DOMContentLoaded", iniciar);
})();
