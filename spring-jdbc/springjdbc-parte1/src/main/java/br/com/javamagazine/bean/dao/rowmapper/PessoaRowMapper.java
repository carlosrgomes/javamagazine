package br.com.javamagazine.bean.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.javamagazine.bean.Pessoa;

public class PessoaRowMapper implements RowMapper<Pessoa> {

	public Pessoa mapRow(ResultSet rs, int rowNum) throws SQLException {
		Pessoa pessoa =  new Pessoa();
		pessoa.setId(rs.getLong("ID"));
		pessoa.setNome(rs.getString("NOME"));
		pessoa.setEmail(rs.getString("EMAIL"));
		return pessoa;
	}

}
