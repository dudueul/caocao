// name: PacketUtil$$SerializeObject
// entry: 016ab10c

/* WARNING: Removing unreachable block (ram,0x016ab310) */
/* WARNING: Removing unreachable block (ram,0x016ab314) */
/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void PacketUtil__SerializeObject(undefined8 param_1,long param_2)

{
  uint uVar1;
  undefined8 uVar2;
  long *plVar3;
  long lVar4;
  undefined1 auVar5 [16];
  undefined8 uStack_80;
  undefined8 uStack_78;
  undefined8 uStack_70;
  undefined8 uStack_68;
  undefined1 auStack_60 [16];
  
  if ((bRam00000000049f961d & 1) == 0) {
    func_0x010c65d4(0x933d);
    bRam00000000049f961d = 1;
  }
  auStack_60._0_8_ = 0;
  auStack_60._8_8_ = 0;
  uStack_68 = 0;
  uStack_78 = 0;
  uStack_70 = 0;
  uStack_80 = 0;
  if (((*(byte *)(_class_PacketUtil_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_PacketUtil_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar4 = **(long **)(_class_PacketUtil_TypeInfo + 0xb8);
  if (lVar4 == 0) {
    func_0x010eec64(0);
  }
  System_Text_StringBuilder__set_Length(lVar4,0,0);
  lVar4 = *(long *)(*(long *)(_class_PacketUtil_TypeInfo + 0xb8) + 8);
  if (lVar4 == 0) {
    func_0x010eec64(0);
  }
  LitJson_JsonWriter__Reset(lVar4,0);
  lVar4 = *(long *)(*(long *)(_class_PacketUtil_TypeInfo + 0xb8) + 8);
  if (lVar4 == 0) {
    func_0x010eec64(0);
  }
  LitJson_JsonWriter__WriteObjectStart(lVar4,0);
  if (param_2 == 0) {
    func_0x010eec64(0);
  }
  System_Collections_Generic_Dictionary<object,_object>__GetEnumerator
            (&uStack_80,param_2,
             _mm_Method_System_Collections_Generic_Dictionary<string,_string>_GetEnumerator__);
  while (uVar1 = func_0x01e14068(&uStack_80,
                                 _mm_Method_System_Collections_Generic_Dictionary_Enumerator<string,_string>_MoveNext__
                                ), (uVar1 & 1) != 0) {
    auVar5 = func_0x01e1420c(&uStack_80,
                             _mm_Method_System_Collections_Generic_Dictionary_Enumerator<string,_string>_get_Current__
                            );
    auStack_60 = auVar5;
    if (((*(byte *)(_class_PacketUtil_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_PacketUtil_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar4 = *(long *)(*(long *)(_class_PacketUtil_TypeInfo + 0xb8) + 8);
    uVar2 = func_0x025ae738(auStack_60,
                            _mm_Method_System_Collections_Generic_KeyValuePair<string,_string>_get_Key__
                           );
    if (lVar4 == 0) {
      func_0x010eec64(0);
    }
    LitJson_JsonWriter__WritePropertyName(lVar4,uVar2,0);
    lVar4 = *(long *)(*(long *)(_class_PacketUtil_TypeInfo + 0xb8) + 8);
    uVar2 = func_0x025ae750(auStack_60,
                            _mm_Method_System_Collections_Generic_KeyValuePair<string,_string>_get_Value__
                           );
    if (lVar4 == 0) {
      func_0x010eec64(0);
    }
    LitJson_JsonWriter__Write(lVar4,uVar2,0);
  }
  func_0x01e144dc(&uStack_80,
                  _mm_Method_System_Collections_Generic_Dictionary_Enumerator<string,_string>_Dispose__
                 );
  if (((*(byte *)(_class_PacketUtil_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_PacketUtil_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar4 = *(long *)(*(long *)(_class_PacketUtil_TypeInfo + 0xb8) + 8);
  if (lVar4 == 0) {
    func_0x010eec64(0);
  }
  LitJson_JsonWriter__WriteObjectEnd(lVar4,0);
  plVar3 = *(long **)(*(long *)(_class_PacketUtil_TypeInfo + 0xb8) + 8);
  if (plVar3 == (long *)0x0) {
    func_0x010eec64(0);
  }
  (**(code **)(*plVar3 + 0x160))(plVar3,*(undefined8 *)(*plVar3 + 0x168));
  return;
}


