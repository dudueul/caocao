namespace HutongGames.PlayMaker.Actions
{
	public class SetName : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmString name;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoSetLayer()
		{
		}
	}
}
