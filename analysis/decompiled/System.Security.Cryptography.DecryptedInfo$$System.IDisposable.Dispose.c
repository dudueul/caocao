// name: System.Security.Cryptography.DecryptedInfo$$System.IDisposable.Dispose
// entry: 01b14614

void System_Security_Cryptography_DecryptedInfo__System_IDisposable_Dispose(long param_1)

{
  if (*(long *)(param_1 + 0x10) != 0) {
    System_Security_Cryptography_SymmetricAlgorithm__Clear(*(long *)(param_1 + 0x10),0);
  }
  if (*(long *)(param_1 + 0x30) != 0) {
    System_IO_Stream__Dispose(*(long *)(param_1 + 0x30),0);
    *(undefined8 *)(param_1 + 0x30) = 0;
  }
  return;
}


