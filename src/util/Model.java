/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author Heni Edrianti
 */
public class Model {
        public static final String flag = "$$1";
        
        public static final String getAllAdmin = "SELECT identifier FROM administrator";
        public static final String getAllPegawai = "SELECT * FROM pegawai";
        public static final String getNameById = "SELECT nama FROM pegawai WHERE npsn = \'"+flag+"\'";
        public static final String getDataById = "SELECT * FROM pegawai WHERE npsn = \'"+flag+"\'";
        public static final String getPassById = "SELECT password FROM administrator WHERE identifier = \'"+flag+"\'";
        public static final String []absentByTodayTableHeader = {"ID","npsn","nama","jam","status","keterangan"};
        public static final String getAbsentByDay = "SELECT k.id,k.npsn,p.nama,k.jam,k.status,k.keterangan FROM kehadiran as k,pegawai as p WHERE k.npsn=p.npsn AND k.tanggal=\""+flag+"\" ORDER BY k.jam ASC";
        public static final String getAllPegawaiName = "SELECT `nama` FROM `pegawai` ORDER BY `nama`";
        
        public static final String insertAbsentData = "INSERT INTO kehadiran (tanggal, jam, npsn, status, keterangan) VALUES ("+flag+")";
        public static int jamPulang = 14;
        
        public static String generateQuery(String query,String parameter){
            return query.replace(flag, parameter);
        }
}