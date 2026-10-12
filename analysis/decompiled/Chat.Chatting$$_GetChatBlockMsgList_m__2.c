// name: Chat.Chatting$$<GetChatBlockMsgList>m__2
// entry: 01d3216c

ulong Chat_Chatting__<GetChatBlockMsgList>m__2(undefined8 param_1,long param_2,long param_3)

{
  int iVar1;
  ulong uVar2;
  int iVar3;
  
  if (param_2 == 0) {
    func_0x010eec64(0);
  }
  iVar1 = *(int *)(param_2 + 0x30);
  if (param_3 == 0) {
    func_0x010eec64(0);
  }
  iVar3 = *(int *)(param_3 + 0x30);
  if (iVar3 < iVar1) {
    uVar2 = 0xffffffff;
  }
  else {
    if (param_2 == 0) {
      func_0x010eec64(0);
      iVar3 = *(int *)(param_3 + 0x30);
    }
    uVar2 = (ulong)(*(int *)(param_2 + 0x30) < iVar3);
  }
  return uVar2;
}


