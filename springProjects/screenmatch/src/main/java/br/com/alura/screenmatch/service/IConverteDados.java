package br.com.alura.screenmatch.service;

public interface IConverteDados {
    <T> T obertDados(String json, Class<T> classe);
}
