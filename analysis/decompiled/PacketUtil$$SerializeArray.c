// name: PacketUtil$$SerializeArray
// entry: 016aad14

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void PacketUtil__SerializeArray(undefined8 param_1,long param_2)

{
  undefined8 uVar1;
  long *plVar2;
  long lVar3;
  uint uVar4;
  
  if ((bRam00000000049f961b & 1) == 0) {
    func_0x010c65d4(0x933b);
    bRam00000000049f961b = 1;
  }
  if (((*(byte *)(_class_PacketUtil_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_PacketUtil_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar3 = **(long **)(_class_PacketUtil_TypeInfo + 0xb8);
  if (lVar3 == 0) {
    func_0x010eec64(0);
  }
  System_Text_StringBuilder__set_Length(lVar3,0,0);
  lVar3 = *(long *)(*(long *)(_class_PacketUtil_TypeInfo + 0xb8) + 8);
  if (lVar3 == 0) {
    func_0x010eec64(0);
  }
  LitJson_JsonWriter__Reset(lVar3,0);
  lVar3 = *(long *)(*(long *)(_class_PacketUtil_TypeInfo + 0xb8) + 8);
  if (lVar3 == 0) {
    func_0x010eec64(0);
  }
  LitJson_JsonWriter__WriteArrayStart(lVar3,0);
  uVar4 = 0;
  while( true ) {
    if (param_2 == 0) {
      func_0x010eec64(0);
    }
    if ((int)*(uint *)(param_2 + 0x18) <= (int)uVar4) break;
    if (*(uint *)(param_2 + 0x18) <= uVar4) {
      uVar1 = func_0x010ef850();
      func_0x010eebec(uVar1,0,0);
    }
    uVar1 = *(undefined8 *)(param_2 + (long)(int)uVar4 * 8 + 0x20);
    if (((*(byte *)(_class_PacketUtil_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_PacketUtil_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar3 = *(long *)(*(long *)(_class_PacketUtil_TypeInfo + 0xb8) + 8);
    if (lVar3 == 0) {
      func_0x010eec64(0);
    }
    LitJson_JsonWriter__Write(lVar3,uVar1,0);
    uVar4 = uVar4 + 1;
  }
  if (((*(byte *)(_class_PacketUtil_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_PacketUtil_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar3 = *(long *)(*(long *)(_class_PacketUtil_TypeInfo + 0xb8) + 8);
  if (lVar3 == 0) {
    func_0x010eec64(0);
  }
  LitJson_JsonWriter__WriteArrayEnd(lVar3,0);
  plVar2 = *(long **)(*(long *)(_class_PacketUtil_TypeInfo + 0xb8) + 8);
  if (plVar2 == (long *)0x0) {
    func_0x010eec64(0);
  }
                    /* WARNING: Could not recover jumptable at 0x016aaedc. Too many branches */
                    /* WARNING: Treating indirect jump as call */
  (**(code **)(*plVar2 + 0x160))(plVar2,*(undefined8 *)(*plVar2 + 0x168));
  return;
}


