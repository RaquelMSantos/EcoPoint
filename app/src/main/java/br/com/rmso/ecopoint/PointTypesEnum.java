package br.com.rmso.ecopoint;

public enum PointTypesEnum {

    ELETONICO("eletrônico"),
    BATERIAS("pilhas e baterias"),
    RECICLAVEIS("metal,isopor,papel,plastico,vidro"),
    MEDICAMENTO("medicamentos"),
    OLEO("óleos de cozinha"),
    OUTROS("eletronico, metal,isopor,papel,plastico,vidro"),
    GERAL("lixo organico, metralhas, móveis, materiais recicláveis, eletroeletronico, utensílios domésticos e resíduos de podas");

    private String types;

    PointTypesEnum(String types){
        this.types = types;
    }

    public String getTypes(){
        return types;
    }
}
