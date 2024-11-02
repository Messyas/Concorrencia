package ZZZZ_ListaDeExercicios3.Ex2;

/**
 * Exercício 2: Mutex com ReentrantLock
 * Modifique o exercício anterior para usar um ReentrantLock em
 * vez de synchronized para proteger o incremento do contador.
 */

// Notas: Reentrantlock é uma classe que fornece uma maneira diferente de se usar threads com sincronizacao, o termo reentrant se refere a thread que ja possui o lock (bloqueio) e pode adiquiri-lo novamente sem problemas.
    //Isso permite que a thread acesse um recurso protegido sem causar deadlock (bloqueio eterno ao recurso).
public class ContadorReentrantLock {

}
