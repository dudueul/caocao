// name: Multiplay.MultiplayManager$$OnDisconnected
// entry: 013a6e48

void Multiplay_MultiplayManager__OnDisconnected(long param_1)

{
  *(undefined2 *)(param_1 + 0x141) = 0;
  if (*(long *)(param_1 + 0x70) != 0) {
    System_Action__Invoke(*(long *)(param_1 + 0x70),0);
    *(undefined8 *)(param_1 + 0x70) = 0;
  }
  return;
}


