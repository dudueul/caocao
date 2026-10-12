namespace HutongGames.PlayMaker.Actions
{
	public class SetRandomRotation : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmBool x;

		[RequiredField]
		public FsmBool y;

		[RequiredField]
		public FsmBool z;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoRandomRotation()
		{
		}
	}
}
