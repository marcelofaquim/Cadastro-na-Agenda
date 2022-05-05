/**
 * 
 */
 
 function validar(){
	let nome = frmContato.nome.value;
	let fone = frmContato.nome.value;
	if (nome == ""){
	alert("campo nome obrigatorio")
	frmContato.nome.focus();
	}
	else
	if(fone === ""){
		alert("Campo fone obrigatorio");
		frmContato.fone.focus();
		}
	else
		document.forms ["frmContato"].submit;
		
	}