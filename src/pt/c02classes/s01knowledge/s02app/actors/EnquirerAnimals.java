package pt.c02classes.s01knowledge.s02app.actors;

import pt.c02classes.s01knowledge.s01base.impl.BaseConhecimento;
import pt.c02classes.s01knowledge.s01base.inter.IBaseConhecimento;
import pt.c02classes.s01knowledge.s01base.inter.IDeclaracao;
import pt.c02classes.s01knowledge.s01base.inter.IEnquirer;
import pt.c02classes.s01knowledge.s01base.inter.IObjetoConhecimento;
import pt.c02classes.s01knowledge.s01base.inter.IResponder;

public class EnquirerAnimals implements IEnquirer 
{

	IResponder responder;
	IObjetoConhecimento obj;
	
	public void connect(IResponder responder) {
		this.responder = responder;
	}
	
	public boolean discover() {
		IBaseConhecimento bc = new BaseConhecimento();
        
        int i = 0, j = 0, k = 0, ja = 0;
        
        String[] bichos = bc.listaNomes();
        String[] perjafeita = new String[42];
        String[] respJaFeita = new String[42];
		obj = bc.recuperaObjeto(bichos[i]);
		IDeclaracao decl = obj.primeira();
		decl = obj.primeira();
		
	while (decl != null) {

		String pergunta = decl.getPropriedade();
		String respostaEsperada = decl.getValor();
		
		if(k != 0){											//temos alguma resposta ja feita
			while(perjafeita[j] != null && ja == 0){
				if(pergunta.equalsIgnoreCase(perjafeita[j])){
					ja = 1;
				}else{
					j++;
				}
			}
		}
			j=0;
		    if(ja==1){										//pergunta ja feita
		    	if (respJaFeita[j].equalsIgnoreCase(respostaEsperada))		//compara respostaEsperada com resposta dada
					decl = obj.proxima();								// Se igual próximo
				else{
					i++;
					obj = bc.recuperaObjeto(bichos[i]);				//prox animal
					decl = obj.primeira();
				}
		    	ja=0;
		    }else{								//pergunta ainda não feita
		    	String resposta = responder.ask(pergunta);
		    	respJaFeita[j] = resposta;
		    	perjafeita[j] = pergunta;
		    	j++;
		    	if (resposta.equalsIgnoreCase(respostaEsperada))		//compara respostaEsperada com resposta dada
					decl = obj.proxima();								// Se igual próximo
				else{
					i++;
					obj = bc.recuperaObjeto(bichos[i]);				//prox animal
					decl = obj.primeira();
				}
		    }
		    k++;
		}
       										
	boolean acertei = responder.finalAnswer(bichos[i]);
	if (acertei)
		System.out.println("Oba! Acertei!");
	else
		System.out.println("fuem! fuem! fuem!");
	return acertei;
	}
	}


