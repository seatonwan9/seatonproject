package com.xearth.sp.seatonproject.gdal;

import org.gdal.ogr.Geometry;

public class GDALWKT2WKB {

    public static void usage() {
        System.out.println("usage example: wkt2wkb \"POINT(47.0 19.2)\"");
        System.exit(-1);
    }

    public static void gdalWKT2WKB(String[] args) {

        if (args.length != 1) usage();

        Geometry geom = Geometry.CreateFromWkt(args[0]);

        long wkbSize = geom.WkbSize();
        byte[] wkb = geom.ExportToWkb();
        if (wkb.length != wkbSize)
        {
            System.exit(-1);
        }
        if (wkbSize > 0)
        {
            System.out.print( "wkt-->wkb: ");
            for(int i=0;i<wkbSize;i++)
            {
                if (i>0)
                    System.out.print("-");
                int val = wkb[i];
                if (val < 0)
                    val = 256 + val;
                String hexVal = Integer.toHexString(val);
                if (hexVal.length() == 1)
                    System.out.print("0");
                System.out.print(hexVal);
            }
            System.out.print("\n");

            // wkb --> wkt (reverse test)
            Geometry geom2 = Geometry.CreateFromWkb(wkb);
            String geom_wkt = geom2.ExportToWkt();
            System.out.println( "wkb->wkt: " + geom_wkt );
        }

        // wkt -- gml transformation
        String gml = geom.ExportToGML();
        System.out.println( "wkt->gml: " + gml );

        Geometry geom3 = Geometry.CreateFromGML(gml);
        String geom_wkt2 = geom3.ExportToWkt();
        System.out.println( "gml->wkt: " + geom_wkt2 );
    }

    public static void main(String[] args) {
        String[] arr = new String[]{"POINT(47.0 19.2)"};
        gdalWKT2WKB(arr);
    }
}
