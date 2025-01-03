package automation;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import data.DataGiven;
import dataProvider.DataProviderInicioSesion;
import io.qameta.allure.Description;
import modelos.ModeloCredenciales;
import pages.BarraNavegacionPage;
import pages.InicioSesionPage;
import utilities.BaseTest;

public class InicioSesionTests extends BaseTest{
    InicioSesionPage inicioSesionPage = new InicioSesionPage(); 

    @BeforeMethod(alwaysRun = true)
    public void setUp(){
        commonFlows.goToLogIn();
    }

    @Description("Verificando la pagina de inicio sesion")
    @Test
    public void verificarPaginaTest(){
        inicioSesionPage.verifyPage();
    }

    @Description("Iniciar sesion con credenciales validas")
    @Test
    public void iniciarSesionConCredencialesValidasTest(){
        ModeloCredenciales credenciales = DataGiven.getCredencialValida(); 
        inicioSesionPage.rellenandoFormulario(credenciales.getUsername(), credenciales.getPassword());
        new BarraNavegacionPage().verificarInicioSesionExitoso(); 
    }

    @Description("Intentar iniciar sesion con credenciales invalidas")
    @Test(dataProvider = DataProviderInicioSesion.DP_DATOSINVALIDOS,dataProviderClass = DataProviderInicioSesion.class)
    public void intentarIniciarSesionConCredencialesInvalidas(String username, String password, String message){ 
        inicioSesionPage.rellenandoFormulario(username,password);
        inicioSesionPage.verificarMensaje(message);
    }
}
