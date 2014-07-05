/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Gestiones;

import CapaDatos.Conexionbd;
import ClasesPojo.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PROFESORES
 */
public class GestionUsuario implements IGestion {
    
        private Usuario usuario;
        Connection conectar;

    public GestionUsuario() {
        
       ConexionBase();
    }
        
     public void ConexionBase()
      {
          try
        {
       usuario=new Usuario(0,"","","");
        Conexionbd.setUsuario("root");
        Conexionbd.setClave("");
       Conexionbd.setCadenaConexion("jdbc:mysql://localhost/usuariodb");
//        conectar=DriverManager.getConnection("jdbc:mysql://localhost/usuariodb","root","");
//        Conexionbd.stCadenaConexion("jdbc:mysql://localhost/usuariodb","root","");
      } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        
      }
      
      

    /**
     * Get the value of usuario
     *
     * @return the value of usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Set the value of usuario
     *
     * @param usuario new value of usuario
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public void Nuevo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
     
     
     
     

    @Override
    public void Insertar() throws SQLException {
        
        try
        {
         Conexionbd.getInstancia().conectar();
         Conexionbd.getInstancia().ejecutar("insert into usuario (Nombre,Apellido,Cedula) values ('"+usuario.getNombre()+"','"+usuario.getApellido()+"','"+usuario.getCedula()+"')");
          
        }
        catch(SQLException ex)
        {
            throw ex;
        }
        finally 
        {
            Conexionbd.getInstancia().desconectar();
        }
        
    }

    @Override
    public void Consultar() throws SQLException {
        try
        {
            Conexionbd.getInstancia().conectar();
            Conexionbd.getInstancia().ejecutar("SELECT `Id`, `Nombre`, `Apellido`, `Cedula` FROM `usuario` WHERE Id="+usuario.getId());
        }
        catch(SQLException ex)
        {
            
        }
        
    }

    @Override
    public void Modificar() throws SQLException {
       try
        {
            Conexionbd.getInstancia().conectar();
            Conexionbd.getInstancia().ejecutar("UPDATE `usuario` SET "
                    + "`nombre`='"+usuario.getNombre()+"',`apellido`='"+usuario.getApellido()+"',"
                    + "`cedula`='"+usuario.getCedula()+"' WHERE id="+usuario.getId());
        }
        catch(SQLException ex)
        {
            //throw ex;
        }
        finally{Conexionbd.getInstancia().desconectar();}
    }
    

    @Override
    public void Eliminar() throws SQLException { 
        try
        {
         Conexionbd.getInstancia().conectar();
         Conexionbd.getInstancia().ejecutar("delete  from usuario  where Id="+usuario.getId()+"" );
        }
        catch(SQLException ex)
        {
            throw ex;
        }
        finally 
        {
            Conexionbd.getInstancia().desconectar();
        }
    }
    
    
}
