using System.Runtime.InteropServices;

[StructLayout((LayoutKind)0, Size = 12)]
public struct Pos2Score
{
	public IntVector2 m_pos;

	public int m_score;

	public Pos2Score(IntVector2 pos, int score)
	{
		m_pos = default(IntVector2);
		m_score = 0;
	}
}
