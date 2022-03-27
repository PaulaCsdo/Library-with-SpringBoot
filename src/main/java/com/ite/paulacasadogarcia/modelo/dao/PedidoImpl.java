package com.ite.paulacasadogarcia.modelo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ite.paulacasadogarcia.modelo.bean.Pedido;
import com.ite.paulacasadogarcia.modelo.repository.PedidoRepository;

@Service
public class PedidoImpl implements PedidoInt{
	
	@Autowired
	private PedidoRepository  perepo;

	@Override
	public int alta(Pedido pedido) {
		perepo.save(pedido);
		return 1;
	}

}
