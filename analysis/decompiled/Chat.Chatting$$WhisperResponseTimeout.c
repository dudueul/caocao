// name: Chat.Chatting$$WhisperResponseTimeout
// entry: 01d2ca84

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

long Chat_Chatting__WhisperResponseTimeout(undefined8 param_1,undefined8 param_2,undefined4 param_3)

{
  long lVar1;
  
  if ((bRam00000000049fcf1f & 1) == 0) {
    func_0x010c65d4(0x25fd);
    bRam00000000049fcf1f = 1;
  }
  lVar1 = func_0x010fdda8(_class_Chat_Chatting_<WhisperResponseTimeout>c__Iterator0_TypeInfo);
  System_Object___ctor(lVar1,0);
  if (lVar1 == 0) {
    func_0x010eec64(0);
    uRam0000000000000010 = param_3;
    func_0x010eec64(0);
  }
  else {
    *(undefined4 *)(lVar1 + 0x10) = param_3;
  }
  *(undefined8 *)(lVar1 + 0x18) = param_1;
  return lVar1;
}


