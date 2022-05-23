package com.company;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AcessoAPI {
    private String conteudoJson;
    private String conteudoInterface;

    private final String BASE_CEP_URL =  "http://educacao.dadosabertosbr.com/api/escolas?nome=";

    public AcessoAPI(String id) throws IOException, JSONException {
        acessaAPI(id);
        trataJSON();
    }

    private void trataJSON() throws JSONException {
        JSONArray jsonObj = new JSONArray(this.conteudoJson);
        System.out.println(jsonObj);
        this.conteudoInterface = "";

        int numero= jsonObj.getInt(0);

        System.out.println("O numero de escolas encontradas é: "+ numero);

        JSONArray vetEscolas = jsonObj.getJSONArray(1);

        System.out.println("Os dados das ecolas encontradas são:");
        for (int x = 0; x<numero;x++) {
            JSONObject escola = vetEscolas.getJSONObject(x);
            String nome = escola.getString("nome");
            System.out.println("Nome: "+nome);
            conteudoInterface += "Nome: " + nome + "\n\n";

            String estado = escola.getString("estado");
            String cidade = escola.getString("cidade");
            System.out.println("Cidade/Estado: "+ cidade+"/"+estado);
            conteudoInterface += "Cidade: "+ cidade + "\n\n" + "Estado: "+ estado + "\n\n";
            String adm = escola.getString("dependenciaAdministrativaTxt");
            System.out.println("Dependência administrativa: "+ adm);
            conteudoInterface += "Dependência administrativa: "+ adm + "\n\n";
            float enem = escola.getInt("enemMediaGeral");
            System.out.println("Média do enem: "+ enem);
            conteudoInterface += "Média do enem: "+ enem + "\n\n";
            System.out.println("----------------------------------------------------");
        }



        /*this.status = jsonObj.getInt("status");
        if(this.status == 200) {
            this.id= jsonObj.getString("id");
        }
        else {
            this.mensagem = jsonObj.getString("message");
        }*/
    }


    public String getConteudoInterface() {
        return conteudoInterface;
    }

    private void acessaAPI(String id) throws IOException {
        URL url = new URL(BASE_CEP_URL + id);

        System.out.println(url);

        HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
        conexao.addRequestProperty("Content-Type", "application/json");
        conexao.addRequestProperty("User-Agent", "Mozilla/4.76");

        BufferedReader entrada = new BufferedReader(new InputStreamReader(conexao.getInputStream()));

        String inputLine;
        conteudoJson = "";
        while( (inputLine = entrada.readLine()) != null) {
            conteudoJson += inputLine;
        }
    }

    public String getConteudoJson() {
        return conteudoJson;
    }


}
