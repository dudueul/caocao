// name: Chat.Chatting$$getLanguage
// entry: 01d278d0

undefined4 Chat_Chatting__getLanguage(undefined8 param_1,int param_2)

{
  long lVar1;
  
  lVar1 = (long)(param_2 + 2);
  if (4 < param_2 - 1U) {
    lVar1 = 0;
  }
  return *(undefined4 *)(&UNK_03997da0 + lVar1 * 4);
}


