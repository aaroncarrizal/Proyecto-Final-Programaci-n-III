package main;

import java.util.ArrayList;
import java.util.Arrays;

public class CaminosMinimos {

    public void creaMAtriz(ArrayList<Paquete> paquetesAEntregar) {
        int a = paquetesAEntregar.size();
        long mAdy[][] = new long[a][a];
        for (int i = 0; i < paquetesAEntregar.size(); i++) {
            for (int j = 0; j < paquetesAEntregar.size(); j++) {
                mAdy[i][j] = calcularDistancia(paquetesAEntregar.get(i).dx, paquetesAEntregar.get(i).dy, paquetesAEntregar.get(j).dx, paquetesAEntregar.get(j).dy);
            }
        }
    }

    public int calcularDistancia(int x1, int y1, int x2, int y2) {//Por arraylis
        return (int) Math.floor(Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2)));
    }

    public ArrayList<int[]> algoritmoFloyd(ArrayList<Paquete> paquetesAEntregar, int asd) { //long [][] mAdy){

        int a = paquetesAEntregar.size();
        long mAdy[][] = new long[a][a];
        for (int i = 0; i < paquetesAEntregar.size(); i++) {
            for (int j = 0; j < paquetesAEntregar.size(); j++) {
                mAdy[i][j] = calcularDistancia(paquetesAEntregar.get(i).dx, paquetesAEntregar.get(i).dy, paquetesAEntregar.get(j).dx, paquetesAEntregar.get(j).dy);
            }
        }
        //agregando obstaculos
//        for (int j = 0; j < a; j++) {
//                //System.out.println("aqui");
//                int r, r2;
//                do{
//                    r = (int) Math.floor(Math.random()*((a-1)-0+1)+0);
//                    r2 = (int)Math.floor(Math.random()*((a-1)-0+1)+0);
//                    //System.out.println("r= "+r+"r2= "+r2);
//                    if(r!=r2){
//                    mAdy[r][r2]=1000000000;
//                    }
//                }while(r==r2);
//                
//            }
        
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < a; j++) {
                System.out.print(mAdy[i][j]+",");
            }
            System.out.println("");
        }
        System.out.println("");
        int vertices = mAdy.length;
        long matrizAdyacencia[][] = mAdy;
        String caminos[][] = new String[vertices][vertices];
        String caminosAuxiliares[][] = new String[vertices][vertices];
        String caminoRecorrido = "", cadena = "", caminitos = "";
        int i, j, k;
        float temporal1, temporal2, temporal3, temporal4, minimo;
        //inicializar matrices y caminos 
        for (i = 0; i < vertices; i++) {
            for (j = 0; j < vertices; j++) {
                caminos[i][j] = "";
                caminosAuxiliares[i][j] = "";
            }
        }

        for (k = 0; k < vertices; k++) {
            for (i = 0; i < vertices; i++) {
                for (j = 0; j < vertices; j++) {

                    temporal1 = matrizAdyacencia[i][j];
                    temporal2 = matrizAdyacencia[i][k];
                    temporal3 = matrizAdyacencia[k][j];
                    temporal4 = temporal2 + temporal3;
                    //encontrando al minimo
                    minimo = Math.min(temporal1, temporal4);
                    if (temporal1 != temporal4) {
                        if (minimo == temporal4) {
                            caminoRecorrido = "";
                            caminosAuxiliares[i][j] = k + "";
                            caminos[i][j] = caminosR(i, k, caminosAuxiliares, caminoRecorrido) + (k);

                        }
                    }
                    matrizAdyacencia[i][j] = (long) minimo;
                }
            }
        }
        //agregando el camino a cadeba
        for (i = 0; i < vertices; i++) {
            for (j = 0; j < vertices; j++) {
                cadena = cadena + "[" + matrizAdyacencia[i][j] + "]";
            }
            cadena = cadena + "\n";
        }
        ArrayList<int[]> bru = new ArrayList<>();
        for (i = 0; i < vertices; i++) {
            for (j = 0; j < vertices; j++) {
                if (matrizAdyacencia[i][j] != 1000000000) {
                    if (i != j) {
                        if (caminos[i][j].equals("")) {
//                            caminitos = "de (" + i+ "---->" +j  + ") irse por...(" + i+ ", " + j+ ")\n";
//                            System.out.println(caminitos);
                            caminitos = paquetesAEntregar.get(i).getId() + "," + paquetesAEntregar.get(j).getId();/////////////////////////////////////////////////caminitos = i + "," + j;
                            int[] numbers = Arrays.asList(caminitos.split(",")).stream().map(String::trim).mapToInt(Integer::parseInt).toArray();
                            //System.out.println("NUMBRES");
                            bru.add(numbers);
                        } else {
//                            caminitos = "de (" + (i) + "---->" + (j) + ") irse por...(" + i + "," + caminos[i][j] + "," + (j) + ")\n";
//                            System.out.println(caminitos);
                            caminitos = paquetesAEntregar.get(i).getId() + "," + caminos[i][j] + "," + paquetesAEntregar.get(j).getId();//////////////////////////////////////caminitos = i + "," + caminos[i][j] + "," + j;
                            int[] numbers = Arrays.asList(caminitos.split(",")).stream().map(String::trim).mapToInt(Integer::parseInt).toArray();
                            //System.out.println("NUMBRES");
                            bru.add(numbers);
                        }
                    }
                }
            }
        }
        //imprime arraylist de arreglos
        for (int[] b : bru) {
            for (int l = 0; l < b.length; l++) {
                System.out.print(b[l] + ", ");
            }
            System.out.println("");
        }
        //int[] numbers = Arrays.asList(caminitos.split(",")).stream().map(String::trim).mapToInt(Integer::parseInt).toArray();
//        return "LA MATRIZ DE CAMINOS MAS CORTOS ENTRE LOS DIFERENTES VERTICES ES \n" + cadena;
        return bru;
        //+ "\n LOS DIFERENTES CAMINOS MAS CORTOS ENTRE VERTICES SON:\n" + caminitos;
    }

    public String caminosR(int i, int k, String[][] caminosAuxiliares, String caminoRecorrido) {
        if (caminosAuxiliares[i][k].equals("")) {
            return "";
        } else {
            caminoRecorrido += caminosR(i, Integer.parseInt(caminosAuxiliares[i][k]), caminosAuxiliares, caminoRecorrido) + (Integer.parseInt(caminosAuxiliares[i][k])) + ",";
            return caminoRecorrido;
        }
    }

//    public static void main(String[] args) {
//
//        long matrizA[][] = {{0, 3, 4, 999999999, 8, 999999999}, {999999999, 0, 999999999,
//            999999999, 5, 999999999}, {999999999, 999999999, 0, 999999999, 3, 999999999},
//        {999999999, 999999999, 999999999, 0, 999999999, 999999999}, {999999999, 999999999,
//            999999999, 7, 0, 3}, {999999999, 999999999, 999999999, 2, 999999999, 0}};
////        CaminosMinimos ruta = new CaminosMinimos();
////        System.out.println(ruta.algoritmoFloyd(matrizA,1));
////        Paquete 
////        ruta.creaMAtriz(matrizA, paquetesAEntregar);
//
//    }

}
