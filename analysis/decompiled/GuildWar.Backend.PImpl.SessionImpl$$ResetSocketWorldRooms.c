// name: GuildWar.Backend.PImpl.SessionImpl$$ResetSocketWorldRooms
// entry: 01b327f8

void GuildWar_Backend_PImpl_SessionImpl__ResetSocketWorldRooms(long param_1)

{
  undefined8 uVar1;
  
  *(undefined4 *)(param_1 + 0xa8) = 0x40a00000;
  if ((0 < *(int *)(param_1 + 0xbc)) && (0 < *(int *)(param_1 + 0xc0))) {
    uVar1 = *(undefined8 *)(param_1 + 0xbc);
    *(undefined8 *)(param_1 + 0xbc) = 0;
    *(undefined8 *)(param_1 + 0xac) = uVar1;
  }
  if ((0 < *(int *)(param_1 + 0xcc)) && (0 < *(int *)(param_1 + 0xd0))) {
    uVar1 = *(undefined8 *)(param_1 + 0xcc);
    *(undefined8 *)(param_1 + 0xcc) = 0;
    *(undefined8 *)(param_1 + 0xac) = uVar1;
  }
  if ((0 < *(int *)(param_1 + 0xc4)) && (0 < *(int *)(param_1 + 200))) {
    uVar1 = *(undefined8 *)(param_1 + 0xc4);
    *(undefined8 *)(param_1 + 0xc4) = 0;
    *(undefined8 *)(param_1 + 0xac) = uVar1;
  }
  if ((*(int *)(param_1 + 0xac) == *(int *)(param_1 + 0xb4)) &&
     (*(int *)(param_1 + 0xb0) == *(int *)(param_1 + 0xb8))) {
    *(undefined8 *)(param_1 + 0xac) = 0;
  }
  *(undefined4 *)(param_1 + 0xb4) = 0;
  *(undefined4 *)(param_1 + 0xb8) = 0;
  return;
}


