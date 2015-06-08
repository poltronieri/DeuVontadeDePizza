package com.dvp.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.map.GeocodeEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.GeocodeResult;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import com.dvp.dao.DAOFactory;
import com.dvp.model.Cardapio;
import com.dvp.model.ItemCardapio;

@ManagedBean
@ViewScoped
public class CatalogoBean {
	
	private List<Cardapio> lista;
	private Cardapio cardapioSelecionado;
	private String pesquisa;
	private String filtroSelecionado = "Item";
	private List<ItemCardapio> itensCardapio;
	
	private MapModel geoModel;
    private String centerGeoMap = "41.850033, -87.6500523";
	private List<ItemCardapio> cardapioSelecionadoItens;
	
    @PostConstruct
    public void inicializar(){
    	geoModel = new DefaultMapModel();
    }
    
    public void onGeocode(GeocodeEvent event) {
        List<GeocodeResult> results = event.getResults();
         
        if (results != null && !results.isEmpty()) {
            LatLng center = results.get(0).getLatLng();
            centerGeoMap = center.getLat() + "," + center.getLng();
             
            for (int i = 0; i < results.size(); i++) {
                GeocodeResult result = results.get(i);
                geoModel.addOverlay(new Marker(result.getLatLng(), result.getAddress()));
            }
        }
    }
    
	public void pesquisaCardapio(){
		lista = new ArrayList<Cardapio>();
		itensCardapio = new ArrayList<ItemCardapio>();
		if (filtroSelecionado.equals("Ingredientes")) {
			List<ItemCardapio> itens = DAOFactory.getItemCardapioDAO().listar(pesquisa);
			
			for (ItemCardapio itemCardapio : itens) {
				itensCardapio.add(itemCardapio);
			}
			System.out.println(itensCardapio);
		}else if (filtroSelecionado.equals("Bairro")) {
			List<ItemCardapio> itens = DAOFactory.getItemCardapioDAO().listarBairro(pesquisa);
			
			for (ItemCardapio itemCardapio : itens) {
				itensCardapio.add(itemCardapio);
			}
		}else if (filtroSelecionado.equals("Cidade")) {
			List<ItemCardapio> itens = DAOFactory.getItemCardapioDAO().listarCidade(pesquisa);
			
			for (ItemCardapio itemCardapio : itens) {
				itensCardapio.add(itemCardapio);
			}
			
		}else if (filtroSelecionado.equals("Nome da pizza")) {
			List<ItemCardapio> itens = DAOFactory.getItemCardapioDAO().listarNomePizza(pesquisa);
			
			for (ItemCardapio itemCardapio : itens) {
				itensCardapio.add(itemCardapio);
			}
			
		}else if (filtroSelecionado.equals("Nome do estabelecimento")) {
			List<ItemCardapio> itens = DAOFactory.getItemCardapioDAO().listarNomeEstabelecimento(pesquisa);
			
			for (ItemCardapio itemCardapio : itens) {
				itensCardapio.add(itemCardapio);
			}
			
		}
	}
	
	public String selecionarCardapio(Cardapio cardapio){
		System.out.println(">>> "+cardapio);
		this.cardapioSelecionado = cardapio;
		this.cardapioSelecionadoItens = DAOFactory.getItemCardapioDAO().listar(cardapioSelecionado);
		return null;
	}
	
	public List<String> carregaFiltros(){
		return Arrays.asList("Bairro", "Cidade" , "Ingredientes", "Nome da pizza", "Nome do estabelecimento");
	}
	
	public List<Cardapio> getLista() {
		return lista;
	}

	public void setLista(List<Cardapio> lista) {
		this.lista = lista;
	}

	public Cardapio getCardapioSelecionado() {
		return cardapioSelecionado;
	}

	public void setCardapioSelecionado(Cardapio cardapioSelecionado) {
		this.cardapioSelecionado = cardapioSelecionado;
	}

	public String getPesquisa() {
		return pesquisa;
	}

	public void setPesquisa(String pesquisa) {
		this.pesquisa = pesquisa;
	}

	public String getFiltroSelecionado() {
		return filtroSelecionado;
	}

	public void setFiltroSelecionado(String filtroSelecionado) {
		this.filtroSelecionado = filtroSelecionado;
	}

	public List<ItemCardapio> getItensCardapio() {
		return itensCardapio;
	}

	public void setItensCardapio(List<ItemCardapio> itensCardapio) {
		this.itensCardapio = itensCardapio;
	}
	
	public MapModel getGeoModel() {
        return geoModel;
    }
 
    public String getCenterGeoMap() {
        return centerGeoMap;
    }

	public List<ItemCardapio> getCardapioSelecionadoItens() {
		return cardapioSelecionadoItens;
	}

	public void setCardapioSelecionadoItens(
			List<ItemCardapio> cardapioSelecionadoItens) {
		this.cardapioSelecionadoItens = cardapioSelecionadoItens;
	}
 
}
