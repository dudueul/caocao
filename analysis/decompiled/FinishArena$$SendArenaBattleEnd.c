// name: FinishArena$$SendArenaBattleEnd
// entry: 01754340

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

long FinishArena__SendArenaBattleEnd(undefined8 param_1)

{
  long lVar1;
  
  if ((bRam00000000049f9c70 & 1) == 0) {
    func_0x010c65d4(0x5731);
    bRam00000000049f9c70 = 1;
  }
  lVar1 = func_0x010fdda8(_class_FinishArena_<SendArenaBattleEnd>c__Iterator0_TypeInfo);
  System_Object___ctor(lVar1,0);
  if (lVar1 == 0) {
    func_0x010eec64(0);
  }
  *(undefined8 *)(lVar1 + 0x40) = param_1;
  return lVar1;
}


