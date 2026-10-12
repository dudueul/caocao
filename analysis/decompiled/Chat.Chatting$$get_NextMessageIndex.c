// name: Chat.Chatting$$get_NextMessageIndex
// entry: 01d27d44

int Chat_Chatting__get_NextMessageIndex(long param_1)

{
  int iVar1;
  
  iVar1 = *(int *)(param_1 + 0x84);
  *(int *)(param_1 + 0x84) = iVar1 + 1;
  return iVar1;
}


