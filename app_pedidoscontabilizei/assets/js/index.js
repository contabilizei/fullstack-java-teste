function escondeElementos(texto) {
	if (texto === 'Pedidos') {
			$('#pedidos').show();
			$('#clientes').hide();
			$('#produtos').hide();
		}

		if (texto === 'Clientes') {
			$('#clientes').show();
			$('#pedidos').hide();
			$('#produtos').hide();
		}

		if (texto === 'Produtos') {
			$('#produtos').show();
			$('#clientes').hide();
			$('#pedidos').hide();
		}
}

$(function() {
	$('.tab-section').on('click', function () {
		var texto = $(this).text();
		escondeElementos(texto);	
	});

	$('.selectpicker').selectpicker({
	  style: 'btn-info',
	  size: 5
	});
});