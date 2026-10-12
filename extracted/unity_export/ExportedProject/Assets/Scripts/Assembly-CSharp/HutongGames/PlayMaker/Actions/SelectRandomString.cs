namespace HutongGames.PlayMaker.Actions
{
	public class SelectRandomString : FsmStateAction
	{
		public FsmString[] strings;

		public FsmFloat[] weights;

		[RequiredField]
		public FsmString storeString;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoSelectRandomString()
		{
		}
	}
}
