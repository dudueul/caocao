// name: Chat.Chatting$$getRoomLocale
// entry: 01d278f4

undefined4 Chat_Chatting__getRoomLocale(undefined8 param_1,int param_2)

{
  long lVar1;
  
  if (param_2 - 1U < 6) {
    lVar1 = (long)*(int *)(&UNK_03997dc0 + (long)(int)(param_2 - 1U) * 4);
  }
  else {
    lVar1 = 0;
  }
  return *(undefined4 *)(&UNK_03997de0 + lVar1 * 4);
}


