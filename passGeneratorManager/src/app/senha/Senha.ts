import { Pessoa } from "../pessoa/Pessoa";
import { Protocolo } from "../protocolo/Protocolo";

export interface Senha {
  id: number;
  idPessoa: number;
  protocolo: Protocolo;
  idProtocolo: number;
  idCartorio: number;
  categoria: string;
  pessoa: Pessoa;
}
