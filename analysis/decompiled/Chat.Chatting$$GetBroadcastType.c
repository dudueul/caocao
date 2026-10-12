// name: Chat.Chatting$$GetBroadcastType
// entry: 01d2d0ac

undefined4 Chat_Chatting__GetBroadcastType(undefined8 param_1,int param_2)

{
  long lVar1;
  
  lVar1 = (long)(param_2 + 2);
  if (2 < param_2 - 1U) {
    lVar1 = 0;
  }
  return *(undefined4 *)(&UNK_03997e00 + lVar1 * 4);
}


