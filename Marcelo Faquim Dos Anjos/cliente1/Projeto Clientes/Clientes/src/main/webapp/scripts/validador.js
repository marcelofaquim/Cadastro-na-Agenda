/**
 * 
 */
 
 function validar(){
	let nome = frmClientes.nome.values;
	let fone = frmClientes.fone.values;
	if (nome ===""){
	alert("Campo nome obrigatorio");
	frmClientes.nome. focus();
	
	}else
	if (nome ===""){
	alert("Campo nome obrigatorio");
	frmClientes.fone. focus();
	
	
	}else
		document.forms["frmclientes"].submit();
}