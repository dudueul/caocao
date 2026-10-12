// name: GuildWar.Backend.PImpl.SessionImpl$$OnSocketUpdateHistory
// entry: 01b32aa0

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void GuildWar_Backend_PImpl_SessionImpl__OnSocketUpdateHistory(long param_1,long param_2)

{
  ushort uVar1;
  int iVar2;
  int iVar3;
  undefined4 uVar4;
  undefined4 uVar5;
  uint uVar6;
  undefined8 uVar7;
  long lVar8;
  code *UNRECOVERED_JUMPTABLE_00;
  ulong uVar9;
  ulong uVar10;
  long *plVar11;
  undefined8 uVar12;
  undefined8 *puVar13;
  long lVar14;
  int *piVar15;
  
  if ((bRam00000000049fbe0e & 1) == 0) {
    func_0x010c65d4(0xc02b);
    bRam00000000049fbe0e = 1;
  }
  if (param_2 == 0) {
    func_0x010eec64(0);
  }
  uVar7 = SocketIO_SocketIOEvent__get_data(param_2,0);
  if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c(_class_Util_TypeInfo);
  }
  iVar2 = Util__GetData(0,uVar7,_str_clanDbShard,0,0);
  if (param_2 == 0) {
    func_0x010eec64(0);
  }
  uVar7 = SocketIO_SocketIOEvent__get_data(param_2,0);
  iVar3 = Util__GetData(0,uVar7,_str_worldID,0,0);
  if (param_2 == 0) {
    func_0x010eec64(0);
  }
  uVar7 = SocketIO_SocketIOEvent__get_data(param_2,0);
  uVar4 = Util__GetData(0,uVar7,_str_fromSeq,0xffffffff,0);
  if (param_2 == 0) {
    func_0x010eec64(0);
  }
  uVar7 = SocketIO_SocketIOEvent__get_data(param_2,0);
  uVar5 = Util__GetData(0,uVar7,_str_toSeq,0xffffffff,0);
  if (param_2 == 0) {
    func_0x010eec64(0);
  }
  lVar8 = SocketIO_SocketIOEvent__get_data(param_2,0);
  if (lVar8 == 0) {
    func_0x010eec64(0);
  }
  uVar7 = LitJson_JsonData__get_Item(lVar8,_str_datas,0);
  if (((*(int *)(param_1 + 0xcc) != iVar2) || (*(int *)(param_1 + 0xd0) != iVar3)) ||
     (lVar8 = *(long *)(param_1 + 0x50), lVar8 == 0)) {
    return;
  }
  if (*(long *)(lVar8 + 0x58) != 0) {
    System_Action<int,_int,_object>__Invoke
              (*(long *)(lVar8 + 0x58),uVar4,uVar5,uVar7,
               _mm_Method_System_Action<int,_int,_JsonData>_Invoke__);
  }
  UNRECOVERED_JUMPTABLE_00 = *(code **)(lVar8 + 0x10);
  plVar11 = *(long **)(lVar8 + 0x20);
  puVar13 = *(undefined8 **)(lVar8 + 0x28);
  func_0x010da364(puVar13);
  uVar6 = func_0x01101d6c(puVar13);
  if ((uVar6 & 1) == 0) {
    if (*(char *)((long)puVar13 + 0x4e) != '\x03') {
                    /* WARNING: Could not recover jumptable at 0x029b5308. Too many branches */
                    /* WARNING: Treating indirect jump as call */
      (*UNRECOVERED_JUMPTABLE_00)(0,plVar11,uVar4,uVar5,uVar7,puVar13);
      return;
    }
    plVar11 = (long *)0x0;
  }
  else if (((*(short *)((long)puVar13 + 0x4c) != -1) &&
           ((plVar11 == (long *)0x0 || ((*(byte *)(*plVar11 + 0x10d) & 1) == 0)))) &&
          (*(long *)(lVar8 + 0x18) != 0)) {
    uVar9 = func_0x01101d58(puVar13);
    func_0x01101dd4(puVar13);
    uVar10 = func_0x010ba65c();
    if ((uVar9 & 1) == 0) {
      uVar1 = *(ushort *)((long)puVar13 + 0x4c);
      if ((uVar10 & 1) == 0) {
        lVar8 = *plVar11 + (ulong)uVar1 * 0x10;
        UNRECOVERED_JUMPTABLE_00 = *(code **)(lVar8 + 0x130);
        puVar13 = *(undefined8 **)(lVar8 + 0x138);
      }
      else {
        lVar8 = func_0x01101dd4(puVar13);
        lVar14 = *plVar11;
        if ((ulong)*(ushort *)(lVar14 + 0x122) != 0) {
          uVar9 = 0;
          piVar15 = (int *)(*(long *)(lVar14 + 0xb0) + 8);
          do {
            if (*(long *)(piVar15 + -2) == lVar8) {
              puVar13 = (undefined8 *)(lVar14 + (ulong)(*piVar15 + (uint)uVar1) * 0x10 + 0x130);
              goto code_r0x029b53d8;
            }
            uVar9 = uVar9 + 1;
            piVar15 = piVar15 + 4;
          } while (uVar9 < *(ushort *)(lVar14 + 0x122));
        }
        puVar13 = (undefined8 *)func_0x010bc584(plVar11,lVar8,(ulong)uVar1);
code_r0x029b53d8:
        UNRECOVERED_JUMPTABLE_00 = (code *)*puVar13;
        puVar13 = (undefined8 *)puVar13[1];
      }
    }
    else {
      if ((uVar10 & 1) == 0) {
        uVar12 = *(undefined8 *)(*plVar11 + (ulong)*(ushort *)((long)puVar13 + 0x4c) * 0x10 + 0x138)
        ;
      }
      else {
        lVar8 = *plVar11;
        if ((ulong)*(ushort *)(lVar8 + 0x122) != 0) {
          uVar9 = 0;
          piVar15 = (int *)(*(long *)(lVar8 + 0xb0) + 8);
          do {
            if (*(long *)(piVar15 + -2) == puVar13[3]) {
              lVar8 = lVar8 + (ulong)(*piVar15 + (uint)*(ushort *)((long)puVar13 + 0x4c)) * 0x10 +
                      0x130;
              goto code_r0x029b539c;
            }
            uVar9 = uVar9 + 1;
            piVar15 = piVar15 + 4;
          } while (uVar9 < *(ushort *)(lVar8 + 0x122));
        }
        lVar8 = func_0x010bc584(plVar11);
code_r0x029b539c:
        uVar12 = *(undefined8 *)(lVar8 + 8);
      }
      puVar13 = (undefined8 *)func_0x010da294(uVar12,puVar13);
      UNRECOVERED_JUMPTABLE_00 = (code *)*puVar13;
    }
  }
                    /* WARNING: Could not recover jumptable at 0x029b52d4. Too many branches */
                    /* WARNING: Treating indirect jump as call */
  (*UNRECOVERED_JUMPTABLE_00)(plVar11,uVar4,uVar5,uVar7,puVar13);
  return;
}


