namespace HutongGames.PlayMaker.Actions
{
	public class FindGameObject : FsmStateAction
	{
		public FsmString objectName;

		public FsmString withTag;

		[RequiredField]
		public FsmGameObject store;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void Find()
		{
		}

		public override string ErrorCheck()
		{
			return null;
		}
	}
}
