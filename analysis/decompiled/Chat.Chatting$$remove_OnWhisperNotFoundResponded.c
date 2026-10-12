// name: Chat.Chatting$$remove_OnWhisperNotFoundResponded
// entry: 01d28d6c

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Chat_Chatting__remove_OnWhisperNotFoundResponded(long param_1,undefined8 param_2)

{
  bool bVar1;
  long *plVar2;
  undefined8 uVar3;
  long lVar4;
  long *plVar5;
  long lVar6;
  undefined8 uStack_38;
  
  if ((bRam00000000049fcf08 & 1) == 0) {
    func_0x010c65d4(0x261f);
    bRam00000000049fcf08 = 1;
  }
  lVar6 = *(long *)(param_1 + 0xd8);
  do {
    plVar2 = (long *)System_Delegate__Remove(0,lVar6,param_2,0);
    plVar5 = (long *)0x0;
    if (plVar2 != (long *)0x0) {
      plVar5 = plVar2;
      if (*plVar2 != _class_System_EventHandler_TypeInfo) {
        func_0x0111ef28(&uStack_38,*(undefined8 *)(*plVar2 + 0x40));
        uVar3 = func_0x010ef81c(uStack_38);
        func_0x010eebec(uVar3,0,0);
        func_0x036457e0(&uStack_38);
        plVar5 = (long *)0x0;
      }
    }
    lVar4 = func_0x0110bbb4((long *)(param_1 + 0xd8),plVar5,lVar6);
    bVar1 = lVar4 != lVar6;
    lVar6 = lVar4;
  } while (bVar1);
  return;
}


