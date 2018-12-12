package br.com.rmso.ecopoint;

import java.util.ArrayList;
import java.util.List;

import br.com.rmso.ecopoint.service.model.Point;
import br.com.rmso.ecopoint.view.activities.ListPointActivity;

public class Utility {

    public static List<Point> mCurrentPointList = new ArrayList<>();
    public static List<Point> mTypeEletronico = new ArrayList<>();
    public static List<Point> mTypeBaterias = new ArrayList<>();
    public static List<Point> mTypeReciclaveis = new ArrayList<>();
    public static List<Point> mTypeMedicamento = new ArrayList<>();
    public static List<Point> mTypeOleo = new ArrayList<>();
    public static List<Point> mTypeOutros = new ArrayList<>();
    public static List<Point> mTypeGeral = new ArrayList<>();
}
