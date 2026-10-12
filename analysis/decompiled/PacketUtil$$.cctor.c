// name: PacketUtil$$.cctor
// entry: 016aa7c8

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void PacketUtil___cctor(void)

{
  undefined4 uVar1;
  undefined8 uVar2;
  undefined8 uVar3;
  
  if ((bRam00000000049f9619 & 1) == 0) {
    func_0x010c65d4(0x933e);
    bRam00000000049f9619 = 1;
  }
  *(undefined4 *)(*(long *)(_class_PacketUtil_TypeInfo + 0xb8) + 0x10) = 0x8000;
  uVar1 = *(undefined4 *)(*(long *)(_class_PacketUtil_TypeInfo + 0xb8) + 0x10);
  uVar2 = func_0x010fdda8(_class_System_Text_StringBuilder_TypeInfo);
  System_Text_StringBuilder___ctor(uVar2,uVar1,0);
  **(undefined8 **)(_class_PacketUtil_TypeInfo + 0xb8) = uVar2;
  uVar3 = **(undefined8 **)(_class_PacketUtil_TypeInfo + 0xb8);
  uVar2 = func_0x010fdda8(_class_LitJson_JsonWriter_TypeInfo);
  LitJson_JsonWriter___ctor(uVar2,uVar3,0);
  *(undefined8 *)(*(long *)(_class_PacketUtil_TypeInfo + 0xb8) + 8) = uVar2;
  return;
}


