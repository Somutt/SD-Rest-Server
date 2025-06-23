package dev.samuel.yugigo.services.response;

import java.util.List;

public class CardAPIResponse {
    // todas as informações da API pública de YuGiOh vem dentro de um array "data" no JSON
    private List<APICard> data;
    public List<APICard> getData() { return data; }
    public void setData(List<APICard> data) { this.data = data; }

    @Override
    public String toString() {
        return "CardAPIResponse{" +
                "data=" + data +
                '}';
    }
}

