namespace HutongGames.PlayMaker.Actions
{
	public class NguiToolsAddChild : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault parent;

		[RequiredField]
		public FsmGameObject childReference;

		public FsmGameObject childInstance;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}
	}
}
