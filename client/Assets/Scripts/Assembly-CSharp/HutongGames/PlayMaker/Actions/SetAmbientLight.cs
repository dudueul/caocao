namespace HutongGames.PlayMaker.Actions
{
	public class SetAmbientLight : FsmStateAction
	{
		[RequiredField]
		public FsmColor ambientColor;

		public bool everyFrame;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		private void DoSetAmbientColor()
		{
		}
	}
}
