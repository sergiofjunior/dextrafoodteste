import com.dextrafoodtest.model.Burger;
import com.dextrafoodtest.model.Ingrediente;
import com.dextrafoodtest.service.HomeService;
import com.dextrafoodtest.util.DataBase;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;

/**
 * Created by Sérgio on 20/04/2017.
 * Teste automatizado do serviço
 */

public class HomeServiceTest {

    @InjectMocks
    HomeService homeService = new HomeService();

    @Mock
    private DataBase dataBase;

    private Burger burger01;

    private Burger burger02;

    private Burger burger03;

    private Burger burger04;

    private Ingrediente ingrediente01;

    private Ingrediente ingrediente02;

    private Ingrediente ingrediente03;

    private Ingrediente ingrediente04;

    private Ingrediente ingrediente05;

    @Before
    public void setUp() {
        listaIngredientesCardapio();
        listaBurgersCardapio();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testaRetornoPrecoHamburgerSimples(){
        Double precoHamburguerSimples = getBurger01().getPreco();
        Mockito.when(dataBase.buscaBurger(getBurger01().getId())).thenReturn(getBurger01());
        Double retornoPrecoHamburguerSimples = homeService.calculaSomenteBurger(getBurger01().getId());
        Assert.assertEquals(retornoPrecoHamburguerSimples, precoHamburguerSimples);
    }

    @Test
    public void testaPromocaoLight(){
        Double precoHamburguer = getBurger02().getPreco();
        Mockito.when(dataBase.buscaBurger(getBurger02().getId())).thenReturn(getBurger02());

        List<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
        Ingrediente alface = getIngrediente01();
        alface.setQtd(1);
        ingredientes.add(alface);

        Integer[] idEQtdAlface = new Integer[2];
        idEQtdAlface[0] = 1; //id ingrediente alface
        idEQtdAlface[1] = 1; //quantidade ingrediente alface
        Mockito.when(dataBase.buscaIngredientes(idEQtdAlface)).thenReturn(ingredientes);
        Mockito.when(dataBase.getIngrediente01()).thenReturn(alface);

        Double retornoPrecoHamburguerPromocaoLight = homeService.calculaPedido(getBurger02().getId(), idEQtdAlface);
        // Preço com 10% de desconto
        precoHamburguer = (precoHamburguer + alface.getPreco()) * 0.9;
        Assert.assertEquals(retornoPrecoHamburguerPromocaoLight, precoHamburguer );
    }

    @Test
    public void testaPromocaoMuitaCarne(){
        Double precoHamburguer = getBurger04().getPreco();
        Mockito.when(dataBase.buscaBurger(getBurger04().getId())).thenReturn(getBurger04());

        List<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
        Ingrediente carne = getIngrediente03();
        carne.setQtd(3);
        ingredientes.add(carne);

        Integer[] idEQtdCarne = new Integer[2];
        idEQtdCarne[0] = 3; //Id Ingrediente Carne
        idEQtdCarne[1] = 3; //Quantidade ingrediente Carne
        Mockito.when(dataBase.buscaIngredientes(idEQtdCarne)).thenReturn(ingredientes);
        Mockito.when(dataBase.getIngrediente03()).thenReturn(carne);

        Double retornoPrecoPromocaoMuitaCarne = homeService.calculaPedido(getBurger04().getId(), idEQtdCarne);
        // Preço de Hamburguer com 3 Porçoes de Carne, cobrando apenas 2 Porções
        precoHamburguer = precoHamburguer + ((carne.getPreco() * (carne.getQtd()))) - carne.getPreco();
        Assert.assertEquals(retornoPrecoPromocaoMuitaCarne, precoHamburguer );
    }

    @Test
    public void testaPromocaoMuitoQueijo(){
        Double precoHamburguer = getBurger03().getPreco();
        Mockito.when(dataBase.buscaBurger(getBurger03().getId())).thenReturn(getBurger03());

        List<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
        Ingrediente queijo = getIngrediente05();
        queijo.setQtd(3);
        ingredientes.add(queijo);

        Integer[] idEQtdQueijo = new Integer[2];
        idEQtdQueijo[0] = 3; //Id Ingrediente queijo
        idEQtdQueijo[1] = 3; //Quantidade ingrediente queijo
        Mockito.when(dataBase.buscaIngredientes(idEQtdQueijo)).thenReturn(ingredientes);
        Mockito.when(dataBase.getIngrediente05()).thenReturn(queijo);

        Double retornoPrecoPromocaoMuitaQueijo = homeService.calculaPedido(getBurger03().getId(), idEQtdQueijo);
        // Preço de Hamburguer com 3 Porçoes de queijo, cobrando apenas 2 Porções
        precoHamburguer = precoHamburguer + ((queijo.getPreco() * (queijo.getQtd()))) - queijo.getPreco();
        Assert.assertEquals(retornoPrecoPromocaoMuitaQueijo, precoHamburguer );
    }


    public ArrayList<Burger> listaBurgersCardapio(){
        ArrayList burgers = new ArrayList();
        Double preco = 0.0;

        setBurger01(new Burger());
        getBurger01().setId(01);
        getBurger01().setNome("X-Bacon");
        List<Ingrediente> ingredientesBurger01 = new ArrayList<Ingrediente>();
        ingredientesBurger01.add(getIngrediente02());
        ingredientesBurger01.add(getIngrediente03());
        ingredientesBurger01.add(getIngrediente05());
        for(Ingrediente ingrediente: ingredientesBurger01){
            preco = preco + ingrediente.getPreco();
        }
        getBurger01().setPreco(preco);
        getBurger01().setIngredientes(ingredientesBurger01);

        setBurger02(new Burger());
        getBurger02().setId(02);
        getBurger02().setNome("X-Burger");
        List<Ingrediente> ingredientesBurger02 = new ArrayList<Ingrediente>();
        ingredientesBurger02.add(getIngrediente03());
        ingredientesBurger02.add(getIngrediente05());
        preco = 0.0;
        for(Ingrediente ingrediente: ingredientesBurger02){
            preco = preco + ingrediente.getPreco();
        }
        getBurger02().setPreco(preco);
        getBurger02().setIngredientes(ingredientesBurger02);

        setBurger03(new Burger());
        getBurger03().setId(03);
        getBurger03().setNome("X-Egg");
        List<Ingrediente> ingredientesBurger03 = new ArrayList<Ingrediente>();
        ingredientesBurger03.add(getIngrediente03());
        ingredientesBurger03.add(getIngrediente04());
        ingredientesBurger03.add(getIngrediente05());
        preco = 0.0;
        for(Ingrediente ingrediente: ingredientesBurger03){
            preco = preco + ingrediente.getPreco();
        }
        getBurger03().setPreco(preco);
        getBurger03().setIngredientes(ingredientesBurger03);

        setBurger04(new Burger());
        getBurger04().setId(04);
        getBurger04().setNome("X-Egg Bacon");
        List<Ingrediente> ingredientesBurger04 = new ArrayList<Ingrediente>();
        ingredientesBurger04.add(getIngrediente02());
        ingredientesBurger04.add(getIngrediente03());
        ingredientesBurger04.add(getIngrediente04());
        ingredientesBurger04.add(getIngrediente05());
        preco = 0.0;
        for(Ingrediente ingrediente: ingredientesBurger04){
            preco = preco + ingrediente.getPreco();
        }
        getBurger04().setPreco(preco);
        getBurger04().setIngredientes(ingredientesBurger04);

        burgers.add(getBurger01());
        burgers.add(getBurger02());
        burgers.add(getBurger03());
        burgers.add(getBurger04());

        return burgers;
    }

    public ArrayList<Ingrediente> listaIngredientesCardapio(){

        ArrayList ingredientes = new ArrayList();
        ingrediente01 = new Ingrediente(1,"Alface", 0.40);
        ingrediente02 = new Ingrediente(2,"Bacon", 2.00);
        ingrediente03 = new Ingrediente(3,"Hamburger de Carne", 3.00);
        ingrediente04 = new Ingrediente(4,"Ovo", 0.80);
        ingrediente05 = new Ingrediente(5,"Queijo", 1.50);

        ingredientes.add(ingrediente01);
        ingredientes.add(ingrediente02);
        ingredientes.add(ingrediente03);
        ingredientes.add(ingrediente04);
        ingredientes.add(ingrediente05);

        return ingredientes;
    }

    public Burger getBurger01() {
        return burger01;
    }

    public void setBurger01(Burger burger01) {
        this.burger01 = burger01;
    }

    public Burger getBurger02() {
        return burger02;
    }

    public void setBurger02(Burger burger02) {
        this.burger02 = burger02;
    }

    public Burger getBurger03() {
        return burger03;
    }

    public void setBurger03(Burger burger03) {
        this.burger03 = burger03;
    }

    public Burger getBurger04() {
        return burger04;
    }

    public void setBurger04(Burger burger04) {
        this.burger04 = burger04;
    }

    public Ingrediente getIngrediente01() {
        return ingrediente01;
    }

    public void setIngrediente01(Ingrediente ingrediente01) {
        this.ingrediente01 = ingrediente01;
    }

    public Ingrediente getIngrediente02() {
        return ingrediente02;
    }

    public void setIngrediente02(Ingrediente ingrediente02) {
        this.ingrediente02 = ingrediente02;
    }

    public Ingrediente getIngrediente03() {
        return ingrediente03;
    }

    public void setIngrediente03(Ingrediente ingrediente03) {
        this.ingrediente03 = ingrediente03;
    }

    public Ingrediente getIngrediente04() {
        return ingrediente04;
    }

    public void setIngrediente04(Ingrediente ingrediente04) {
        this.ingrediente04 = ingrediente04;
    }

    public Ingrediente getIngrediente05() {
        return ingrediente05;
    }

    public void setIngrediente05(Ingrediente ingrediente05) {
        this.ingrediente05 = ingrediente05;
    }
}