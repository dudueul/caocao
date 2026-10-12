namespace HutongGames.PlayMaker.Actions
{
	public class SetAllFsmGameObject : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public bool everyFrame;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoSetFsmGameObject()
		{
		}
	}
}
