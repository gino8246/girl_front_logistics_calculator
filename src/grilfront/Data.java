/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grilfront;

import java.util.Vector;

/**
 *
 * @author user
 */
public class Data {
    class DataBase {
        int code ;
        int level ;
        int people ;
        int man ;
        int amm ;
        int rat ;
        int com ;
        int time ;
        int QB ;
        int QF ;
        int BP ;
        int EP ;
        DataBase( int code, int lv, int peo, int m, int a, int r, int c, int t, int QB, int QF, int BP, int EP ) {
            this.code = code ;
            this.level = lv ;
            this.people = peo ;
            this.man = m ;
            this.amm = a ;
            this.rat = r ;
            this.com = c ;
            this.time = t ;
            this.QB = QB ;
            this.QF = QF ;
            this.BP = BP ;
            this.EP = EP ;
        } // DataBase()        
        
        float Rate( int m, int a, int r, int c, int t ) {
            if (t == 0 || time > t )            
                t = time ;
            
            m*=man ;
            a*=amm ;
            r*=rat ;
            c*=com ;
            //System.out.println( " m:" + m +" a:" + a +" r:" + r + " c:" + c + " t:" + t );
            float total = m+a+r+c ;
            total = total *100 ;
            total = total / t * 60 ;
            int itotal = (int) total ;
            total = ( float ) itotal / 100 ;
            return total ;            
        } // Rate()
    } // class DataBase{}
    
    Vector<DataBase> DataTable ;
    Data() {
        DataTable = new Vector<DataBase>() ;
        int[][] array = { {1,40,4,0,145,145,0,50,20,50,0,0},
                          {2,45,5,550,0,0,350,180,0,0,50,0},
                          {3,45,5,900,900,900,250,720,100,0,0,100},
                          {4,50,5,0,1200,800,750,1440,0,0,0,0},
                          {5,1,2,10,30,15,0,15,0,0,0,0},
                          {6,3,2,0,40,60,0,30,0,0,0,0},
                          {7,5,3,30,0,30,10,60,0,60,0,0},
                          {8,6,5,160,160,0,0,120,0,0,20,0},
                          {9,5,3,100,0,0,30,40,0,0,0,0},
                          {10,8,4,60,200,80,0,90,0,30,0,0},
                          {11,10,5,10,10,10,230,240,50,50,0,0},
                          {12,15,5,0,250,600,60,360,0,0,80,0},
                          {13,12,4,50,0,75,0,20,0,0,0,0},
                          {14,20,5,0,120,70,30,45,0,0,0,0},
                          {15,15,4,0,300,0,0,90,40,45,0,0},
                          {16,25,5,0,0,300,300,300,0,0,75,80},
                          {17,30,4,0,185,185,0,60,0,0,0,60},
                          {18,35,5,0,0,0,210,120,50,0,0,0},
                          {19,40,5,800,550,0,0,360,0,30,70,0},
                          {20,40,5,400,400,400,150,480,100,0,0,0},
                          {21,30,4,0,0,100,45,30,0,0,0,0},
                          {22,35,5,0,600,300,0,150,0,50,0,0},
                          {23,40,5,800,400,400,0,240,0,0,0,70},
                          {24,40,5,100,0,0,700,420,0,0,40,0},
                          {25,35,5,300,300,0,100,120,0,0,0,0},
                          {26,40,5,0,200,550,100,180,30,60,0,0},
                          {27,45,5,0,0,200,500,300,0,0,0,80},
                          {28,45,5,800,800,800,0,720,0,0,0,0},
                          {29,40,5,650,0,650,0,150,0,0,0,0},
                          {30,45,5,0,650,0,300,240,0,1,1,0},
                          {31,50,5,900,600,600,0,330,0,0,0,1},
                          {32,50,5,250,250,250,600,480,1,0,0,0}
                         } ;
        for ( int i = 0; i < 32; i++ ) {
            DataTable.add( new DataBase(array[i][0],array[i][1],array[i][2],array[i][3],array[i][4],array[i][5],array[i][6],array[i][7],array[i][8],
                                        array[i][9],array[i][10],array[i][11]) ) ;
        }
        
    } // Data
    
    String Do( String Sman, String Samm, String Srat, String Scom, String Shour, String Smin, boolean timelim ) {
        String output = "";
        int iman, iamm, irat, icom, ihour, imin ;
        try {
            iman = Integer.parseInt( Sman ) ;
            iamm = Integer.parseInt( Samm ) ;
            irat = Integer.parseInt( Srat ) ;
            icom = Integer.parseInt( Scom ) ;
            ihour = Integer.parseInt( Shour) ;
            imin = Integer.parseInt( Smin ) ;
        } // try
        catch( Exception ex ) {
            return "輸入必須為整數" ;
        } // catch
        
        int time = ihour*60 + imin ;
        
        for (int i = 0 ; i < this.DataTable.size() - 1 ; i++) {
            for (int j = 0 ; j < this.DataTable.size() -1  ; j++) {
                if ( this.DataTable.get( j ).Rate( iman, iamm, irat, icom, time ) < this.DataTable.get( j + 1 ).Rate( iman, iamm, irat, icom, time ) ) {
                    DataBase temp = DataTable.get( j ) ;
                    this.DataTable.setElementAt( DataTable.get( j+1 ), j );
                    this.DataTable.setElementAt( temp, j+1 );
                } // if
            } // for
        } // for
        
        
        output = "章節\t時間\t人口\t彈藥\t口糧\t零件\t評分\n" ;
        for (int i = 0; i < this.DataTable.size() ; i++) {
            DataBase db= DataTable.get( i );
            if (timelim && db.time>time )
               ;
            else
                output+= (db.code-1)/4 + "-" + ((db.code-1)%4+1) + "\t" + db.time/60 + ":" + db.time%60 + "\t" + db.man + "\t" + db.amm + "\t" + db.rat + "\t" + db.com + "\t" + db.Rate(iman, iamm, irat, icom, time) +"\n" ;
            
        } // for
        return output ;
    } // Do()
    Vector<String> DoList( String Sman, String Samm, String Srat, String Scom, String Shour, String Smin, boolean timelim ) {
        Vector<String> output = new Vector() ;
        int iman, iamm, irat, icom, ihour, imin ;
        try {
            iman = Integer.parseInt( Sman ) ;
            iamm = Integer.parseInt( Samm ) ;
            irat = Integer.parseInt( Srat ) ;
            icom = Integer.parseInt( Scom ) ;
            ihour = Integer.parseInt( Shour) ;
            imin = Integer.parseInt( Smin ) ;
        } // try
        catch( Exception ex ) {
            output.add( "輸入必須為整數" );
            return output ;
        } // catch
        
        int time = ihour*60 + imin ;
        
        for (int i = 0 ; i < this.DataTable.size() - 1 ; i++) {
            for (int j = 0 ; j < this.DataTable.size() -1  ; j++) {
                if ( this.DataTable.get( j ).Rate( iman, iamm, irat, icom, time ) < this.DataTable.get( j + 1 ).Rate( iman, iamm, irat, icom, time ) ) {
                    DataBase temp = DataTable.get( j ) ;
                    this.DataTable.setElementAt( DataTable.get( j+1 ), j );
                    this.DataTable.setElementAt( temp, j+1 );
                } // if
            } // for
        } // for
        
        
        output.add( "章節\t時間\t人口\t彈藥\t口糧\t零件\t評分" ) ;
        for (int i = 0; i < this.DataTable.size() ; i++) {
            DataBase db= DataTable.get( i );
            if (timelim && db.time>time )
                 ;
            else
                output.add( (db.code-1)/4 + "-" + db.code % 5 + "\t" + db.time/60 + ":" + db.time%60 + "\t" + db.man + "\t" + db.amm + "\t" + db.rat + "\t" + db.com + "\t" + (int)db.Rate(iman, iamm, irat, icom, time) ) ;
            
        } // for
        return output ;
    } // Do()
}
