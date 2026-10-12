namespace HutongGames.PlayMaker.Actions
{
	public class SetLayer : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public int layer;

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
