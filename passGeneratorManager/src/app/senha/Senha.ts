import { Pessoa } from "../pessoa/Pessoa";

export interface Senha {
  id: number;
  idPessoa: number;
  protocolo: string;
  idCartorio: number;
  categoria: string;
  pessoa: Pessoa;
}
