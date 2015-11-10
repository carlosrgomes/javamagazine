package br.com.javamagazine.bean.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.javamagazine.bean.Pessoa;
import br.com.javamagazine.bean.Telefone;
import br.com.javamagazine.bean.TipoTelefone;

public class PessoaRowMapper implements RowMapper<Pessoa> {

	public Pessoa mapRow(ResultSet rs, int rowNum) throws SQLException {
		Pessoa pessoa =  new Pessoa();
		pessoa.setId(rs.getLong("ID"));
		pessoa.setNome(rs.getString("NOME"));
		pessoa.setEmail(rs.getString("EMAIL"));
		String numeroTelefone = rs.getString("NUMERO");
		
		if(numeroTelefone != null){
			Telefone telefone = new Telefone();
			telefone.setDdd(rs.getString("DDD"));
			telefone.setNumero(numeroTelefone);
			telefone.setId(rs.getLong("ID"));
			telefone.setTipoTelefone(TipoTelefone.findBy(rs.getString("TIPO")));
			telefone.setIdPessoa(rs.getLong("ID_PESSOA"));
			pessoa.setTelefone(telefone);
		}
		return pessoa;
	}

}
