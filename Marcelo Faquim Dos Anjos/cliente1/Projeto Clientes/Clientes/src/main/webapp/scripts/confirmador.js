/**
 * 
 */
 
 function confirmar(idcon){
	let resposta = confirm("Deseja excluir este cliente ?");
	if(resposta === true){
		//alert(idcon)
		window.location.href = "delete?idcon=" + idcon;
	}
}