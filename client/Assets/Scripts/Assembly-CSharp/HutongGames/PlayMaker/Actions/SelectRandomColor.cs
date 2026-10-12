namespace HutongGames.PlayMaker.Actions
{
	public class SelectRandomColor : FsmStateAction
	{
		public FsmColor[] colors;

		public FsmFloat[] weights;

		[RequiredField]
		public FsmColor storeColor;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoSelectRandomColor()
		{
		}
	}
}
