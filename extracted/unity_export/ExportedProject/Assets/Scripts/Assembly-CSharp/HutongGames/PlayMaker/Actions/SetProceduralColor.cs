namespace HutongGames.PlayMaker.Actions
{
	public class SetProceduralColor : FsmStateAction
	{
		[RequiredField]
		public FsmMaterial substanceMaterial;

		[RequiredField]
		public FsmString colorProperty;

		[RequiredField]
		public FsmColor colorValue;

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

		private void DoSetProceduralFloat()
		{
		}
	}
}
