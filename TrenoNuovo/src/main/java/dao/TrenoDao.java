package dao;

import java.util.List;

import dto.TrenoDTO;
import dto.UtenteDTO;
import treno.Treno;

public interface TrenoDao{
	public TrenoDTO add(Treno treno, UtenteDTO utente, String tipo);
	public void deleteTreno(int id);
	public List<TrenoDTO> listaTreni();
	public List<TrenoDTO> listaTreniFinali();
}
